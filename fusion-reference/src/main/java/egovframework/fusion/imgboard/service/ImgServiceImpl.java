package egovframework.fusion.imgboard.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.fusion.imgboard.vo.ImgVO;

@Service
public class ImgServiceImpl extends EgovAbstractServiceImpl implements ImgService{

	@Autowired
	ImgMapper imgMapper;
	
	@Override
	public List<ImgVO> getImgInfo(ImgVO imgVo) {
		return imgMapper.getImgInfo(imgVo);
	}

	@Override
	public int insImg(List<ImgVO> imageInfo) {
		return imgMapper.insImg(imageInfo);
	}

	@Override
	public List<ImgVO> getImgDetailInfo(int imgBoardId) {
		return imgMapper.getImgDetailInfo(imgBoardId);
	}

	@Override
	public String getImgPath(int imgId) {
		
		return imgMapper.getImgPath(imgId);
	}

	@Override
	public void delImg(int imgId) {
		imgMapper.delImg(imgId);
	}

	@Override
	public void delImgMulti(List<Integer> imgIds) {
		imgMapper.delImgMulti(imgIds);
	}

	

}