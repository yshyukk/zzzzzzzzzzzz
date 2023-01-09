package egovframework.fusion.imgboard.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.fusion.comm.vo.PagingVO;
import egovframework.fusion.imgboard.dto.ImgBoardListDTO;
import egovframework.fusion.imgboard.vo.ImgBoardVO;

@Service
public class ImgBoardServiceImpl extends EgovAbstractServiceImpl implements ImgBoardService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgBoardServiceImpl.class);

	@Autowired
	ImgBoardMapper imgBoardMapper;
	
	@Override
	public List<ImgBoardListDTO> getImgBoard(PagingVO pagingVo) {
		return imgBoardMapper.getImgBoard(pagingVo);
	}

	@Override
	public int insImgBoard(ImgBoardVO imgBoardVo) {
		return imgBoardMapper.insImgBoard(imgBoardVo);

	}
	@Override
	public int getMaxBoardId(String userId) {
		
		return imgBoardMapper.getMaxBoardId(userId);
	}

	@Override
	public void insThumbNail(int imgBoardId) {
		imgBoardMapper.insThumbNail(imgBoardId);
	}

	@Override
	public int searchTotal(ImgBoardVO imgBoardVo) {	
		return imgBoardMapper.searchTotal(imgBoardVo);
	}

	@Override
	public int searchTotalCount(PagingVO pagingVo) {
		return imgBoardMapper.searchTotalCount(pagingVo);
	}

	@Override
	public ImgBoardVO getImgBoardDetailInfo(int imgBoardId) {
		return imgBoardMapper.getImgBoardDetailInfo(imgBoardId);
	}

	@Override
	public void updateContents(ImgBoardVO imgBoardVo) {
		imgBoardMapper.updateContents(imgBoardVo);		
	}

	@Override
	public void delImgBoard(int imgBoardId) {
		imgBoardMapper.delImgBoard(imgBoardId);
	}

	


}