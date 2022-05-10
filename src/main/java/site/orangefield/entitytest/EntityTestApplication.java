package site.orangefield.entitytest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import site.orangefield.entitytest.domain.Board;
import site.orangefield.entitytest.domain.BoardRepository;

@Configuration
@SpringBootApplication
public class EntityTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityTestApplication.class, args);
	}

	// Bean이 메모리에 올려줌. 실행시점은 서버 최초 시작시 한 번 - 더미 데이터
	@Bean
	public CommandLineRunner initData(BoardRepository boardRepository) {
		return (args) -> {
			List<Board> boards = new ArrayList<>();

			for (int i = 1; i < 3; i++) {
				Board board = new Board();
				board.setTitle("제목" + i);
				board.setContent("내용" + i);
				boards.add(board);
			}

			boardRepository.saveAll(boards);
		};
	}
}
