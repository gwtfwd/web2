package egovframework.example.drink.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.drink.service.DrinkDefaultVO;
import egovframework.example.drink.service.DrinkService;
import egovframework.example.drink.service.DrinkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("drinkService")

public class DrinkServiceImpl extends EgovAbstractServiceImpl implements DrinkService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DrinkServiceImpl.class);
	
	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "drinkDAO")
	private DrinkDAO drinkDAO;
	// TODO mybatis 사용
	//  @Resource(name="sampleMapper")
	//	private SampleMapper sampleDAO;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	
	/**
	 * 글 목록 조회
	 * @param findVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @throws Exception
	 */
	public List<?> selectDrinkList(DrinkDefaultVO findVO) throws Exception {
		return drinkDAO.selectDrinkList(findVO);
	}
	
	/**
	 * 글 총 개수 조회
	 * @param findVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 */
	@Override
	public int selectDrinkListTotCnt(DrinkDefaultVO findVO){
		return drinkDAO.selectDrinkListTotCnt(findVO);
	}
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public String insertDrink(DrinkVO vo) throws Exception {
		LOGGER.debug(vo.toString());

		/** ID Generation Service */
		String id = egovIdGnrService.getNextStringId();
		vo.setId(id);
		LOGGER.debug(vo.toString());

		drinkDAO.insertDrink(vo);
		return id;
	}

	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateDrink(DrinkVO vo) throws Exception {
		drinkDAO.updateDrink(vo);
	}

	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deleteDrink(DrinkVO vo) throws Exception {
		drinkDAO.deleteDrink(vo);
	}

	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public DrinkVO selectDrink(DrinkVO vo) throws Exception {
		DrinkVO resultVO = drinkDAO.selectDrink(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}
	
	
}

