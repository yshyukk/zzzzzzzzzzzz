package egovframework.fusion.user.service;


import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.fusion.board.service.BoardServiceImpl;
import egovframework.fusion.user.vo.UserVO;

@Service
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	UserMapper userMapper;
	
	@Override
	public void insUser(UserVO userVO) {
		userMapper.insUser(userVO);
	}

	@Override
	public UserVO getLoginInfo(UserVO userVO) {	
		return userMapper.getLoginInfo(userVO);
	}

	@Override
	public int checkId(String checkId) {
		
		return userMapper.checkId(checkId);
	}

	@Override
	public int checkPwd(UserVO userVO) {
		return userMapper.checkPwd(userVO);
	}
	
	
}
