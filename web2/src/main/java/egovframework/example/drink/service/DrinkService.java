package egovframework.example.drink.service;

import java.util.List;


public interface DrinkService {

	/**
	 * 글 목록 조회
	 * @param findVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @throws Exception
	 */
	List<?> selectDrinkList(DrinkDefaultVO findVO) throws Exception;
	
	/**
	 * 글 총 개수 조회
	 * @param findVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 */
	int selectDrinkListTotCnt(DrinkDefaultVO findVO);
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 DrinkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertDrink(DrinkVO vo) throws Exception;

	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 DrinkVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	DrinkVO selectDrink(DrinkVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 DrinkVO
	 * @return void형
	 * @exception Exception
	 */
	void updateDrink(DrinkVO vo) throws Exception;

	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 DrinkVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteDrink(DrinkVO vo) throws Exception;

	
	
	
}

