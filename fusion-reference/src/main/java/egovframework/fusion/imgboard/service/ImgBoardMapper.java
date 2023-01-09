package egovframework.fusion.imgboard.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.fusion.comm.vo.PagingVO;
import egovframework.fusion.imgboard.dto.ImgBoardListDTO;
import egovframework.fusion.imgboard.vo.ImgBoardVO;

@Mapper
public interface ImgBoardMapper {
	
	public List<ImgBoardListDTO> getImgBoard(PagingVO pagingVo);
	
	public int insImgBoard(ImgBoardVO imgBoardVo);
	
	public int getMaxBoardId(String userId);
	
	public void insThumbNail(int imgBoardId);
	
	public int searchTotal(ImgBoardVO imgBoardVo);	
	
	public int searchTotalCount(PagingVO pagingVo);
	
	public ImgBoardVO getImgBoardDetailInfo(int imgBoardId);
	
	public void updateContents(ImgBoardVO imgBoardVo);
	
	public void delImgBoard(int imgBoardId);


}