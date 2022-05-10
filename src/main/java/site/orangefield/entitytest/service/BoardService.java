package site.orangefield.entitytest.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.orangefield.entitytest.domain.Board;
import site.orangefield.entitytest.domain.BoardImplRepository;
import site.orangefield.entitytest.domain.BoardRepository;
import site.orangefield.entitytest.web.dto.BoardDetailRespDto;
import site.orangefield.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository; // API
    private final BoardImplRepository boardImplRepository; // JPQL

    public Board 상세보기(Integer id) {
        Board boardEntity = boardRepository.findById(id).get();

        BoardRespDto dto = new BoardRespDto(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getContent());

        return boardEntity;
    }

    public BoardDetailRespDto 좋아요포함상세보기(Integer id) {
        return boardImplRepository.mFindDetail(id);
    }

}
