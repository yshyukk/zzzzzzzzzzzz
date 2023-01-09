package egovframework.fusion.user.service;



import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.fusion.user.vo.UserVO;


@Mapper
public interface UserMapper {
	/*회원가입*/
	public void insUser(UserVO userVo);
	
	/*로그인 처리*/
	public UserVO getLoginInfo(UserVO userVo);
	
	/*아이디 체크*/
	public int checkId(String checkId);
	
	/*비밀번호 체크*/
	public int checkPwd(UserVO userVo);
}