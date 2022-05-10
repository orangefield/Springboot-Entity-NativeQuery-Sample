package site.orangefield.entitytest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.orangefield.entitytest.domain.Board;
import site.orangefield.entitytest.domain.BoardJPQLRepository;
import site.orangefield.entitytest.domain.BoardRepository;
import site.orangefield.entitytest.web.dto.BoardDetailRespDto;
import site.orangefield.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository; // API
    private final BoardJPQLRepository boardJPQLRepository; // JPQL

    public BoardRespDto 상세보기(Integer id) {
        Board boardEntity = boardRepository.findById(id).get();

        BoardRespDto dto = new BoardRespDto(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getContent());

        return dto; // Entity가 아니라 DTO를 리턴해야.
    }

    public BoardDetailRespDto 좋아요포함상세보기(Integer id) {
        return boardJPQLRepository.mFindDetail(id);
    }

    public List<BoardDetailRespDto> 전체보기() {
        return boardJPQLRepository.mFindAll();
    }

}
