package com.hamryt.helparty.service.gymboard;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.board.gymboard.GymBoardDTO;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.request.UpdateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardResponse;
import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.dto.gym.SimpleGymInfo;
import com.hamryt.helparty.exception.board.gymboard.GymBoardNotFoundException;
import com.hamryt.helparty.exception.board.gymboard.InsertGymBoardFailedException;
import com.hamryt.helparty.exception.board.gymboard.UpdateGymBoardFailedException;
import com.hamryt.helparty.mapper.GymBoardMapper;
import com.hamryt.helparty.service.product.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class GymBoardServiceImplTest {

    @InjectMocks
    private GymBoardServiceImpl gymBoardService;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private GymBoardMapper gymBoardMapper;

    String title = "test";
    String content = "test";
    String price = "test";
    String scope = "test";

    @Test
    @DisplayName("운동 시설 게시물 상세 조회 성공")
    public void getGymBoard_Success() {

        mockFindGymBoardById();

        GetGymBoardResponse getGymBoardResponse = gymBoardService.getGymBoard(1004L);

        assertEquals(getGymBoardResponse.getGymName(), "test");
    }

    @Test
    @DisplayName("운동 시설 게시물 상세조회 실패 : 데이터베이스 select 명령시 null을 반환하면 GymBoardNotFoundException를 던진다. ")
    public void getGymBoard_Fail_GymBoardNotFoundException() {

        given(gymBoardMapper.findGymBoardById(eq(1004L))).willReturn(null);

        GymBoardNotFoundException gymBoardNotFoundException
            = assertThrows(GymBoardNotFoundException.class,
            () -> gymBoardService.getGymBoard(1004L));

        assertEquals("404 NOT_FOUND \"Not found Gymboard. \"",
            gymBoardNotFoundException.getMessage());
    }

    @Test
    @DisplayName("운동 시설 게시물 리스트 조회 성공하면 List<GetGymBoardResponse>를 반환한다. ")
    public void getGymBoards_Success() {

        mockFindGymBoardsByPage();

        List<GetGymBoardResponse> getGymBoardsResponse =
            gymBoardService.getGymBoards(0, 10);

        assertEquals(getGymBoardsResponse.get(0).getContent(), "test");
    }

    @Test
    @DisplayName("운동 시설 게시물 추가 로직 성공")
    public void insertGymBoard_Success() {

        ProductDTO mockSimpleProduct =
            getProductDTO(1L, title, content, price, scope, BoardType.GYM);

        List<ProductDTO> mockProductList = new ArrayList<>();
        mockProductList.add(mockSimpleProduct);

        CreateGymBoardRequest createGymBoardRequest =
            getCreateGymBoardRequest(title, content, mockProductList);

        doNothing().when(productService)
            .insertProductList(eq(createGymBoardRequest.getProductList()), any());

        given(gymBoardMapper.insertGymBoard(any())).willReturn(1);

        gymBoardService.insertGymBoard(createGymBoardRequest, 1004L);

    }

    @Test
    @DisplayName("운동 시설 게시물 추가 로직 실패 : 데이터베이스 insert 명령에 실패하면 InsertGymBoardFailedException을 발생시킨다.")
    public void insertGymBoard_Fail_InsertGymBoardFailedException() {

        ProductDTO mockProduct =
            getProductDTO(1L, title, content, price, scope, BoardType.GYM);

        List<ProductDTO> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);

        CreateGymBoardRequest createGymBoardRequest =
            getCreateGymBoardRequest(title, content, mockProductList);

        given(gymBoardMapper.insertGymBoard(any())).willReturn(0);

        InsertGymBoardFailedException insertGymBoardFailedExcetpion
            = assertThrows(InsertGymBoardFailedException.class,
            () -> gymBoardService.insertGymBoard(createGymBoardRequest, 1004L));

        assertEquals("Insert GymBoard Failed Exception.",
            insertGymBoardFailedExcetpion.getMessage());

    }

    @Test
    @DisplayName("운동 시설 게시물 업데이트 성공")
    public void updateGymBoards_Success() {

        // given
        String title = "test";
        String content = "test";
        UserType userType = UserType.GYM;

        UpdateGymBoardRequest updateGymBoardRequest = createUpdateGymBoardRequest(title, content, userType);

        given(gymBoardMapper.findGymIdByGymBoardId(1004)).willReturn(1000L);
        given(gymBoardMapper.updateGymBoard(any())).willReturn(1);


        gymBoardService.updateGymBoard(1004, 1000L, updateGymBoardRequest);
    }

    @Test
    @DisplayName("운동 시설 게시물 업데이트 실패 : 데이터베이스 update 명령에 실패하면 UpdateGymBoardFailedException을 발생시킨다.")
    public void updateGymBoard_Fail_UpdateGymBoardFailedException() {
        String title = "test";
        String content = "test";
        UserType userType = UserType.GYM;

        UpdateGymBoardRequest updateGymBoardRequest = createUpdateGymBoardRequest(title, content, userType);

        given(gymBoardMapper.findGymIdByGymBoardId(1004)).willReturn(1000L);
        given(gymBoardMapper.updateGymBoard(any())).willReturn(0);

        UpdateGymBoardFailedException updateGymBoardFailedException
            = assertThrows(UpdateGymBoardFailedException.class,
            () -> gymBoardService.updateGymBoard(1004, 1000L, updateGymBoardRequest));

        assertEquals("데이터베이스에 GymBoard update 실패. GymBoardId: 1004", updateGymBoardFailedException.getMessage());
    }

    private UpdateGymBoardRequest createUpdateGymBoardRequest(String title, String content, UserType userType) {
        return UpdateGymBoardRequest.builder()
            .title(title)
            .content(content)
            .userType(userType)
            .build();
    }


    private void mockFindGymBoardById() {

        given(gymBoardMapper.findGymBoardById(eq(1004L))).willReturn(createGymBoardDTO());
    }

    private void mockFindGymBoardsByPage() {

        List<GymBoardDTO> getGymBoardList = new ArrayList<>();
        getGymBoardList.add(createGymBoardDTO());

        given(gymBoardMapper.findGymBoardsByPage(0, 10)).willReturn(getGymBoardList);
    }

    private GymBoardDTO createGymBoardDTO() {
        SimpleGymInfo mockSimpleGymInfo = SimpleGymInfo.builder()
            .gymName("test")
            .phoneNumber("test")
            .addressDetail("test")
            .addressCode("test")
            .build();

        return GymBoardDTO.builder()
            .title("test")
            .gymInfo(mockSimpleGymInfo)
            .content("test")
            .build();
    }

    private CreateGymBoardRequest getCreateGymBoardRequest(
        String title, String content,
        List<ProductDTO> productDTOList
    ) {
        return CreateGymBoardRequest.builder()
            .title(title)
            .content(content)
            .userType(UserType.GYM)
            .productList(productDTOList)
            .build();
    }

    private ProductDTO getProductDTO(
        Long id, String title, String content, String price,
        String scope, BoardType gym
    ) {
        return ProductDTO.builder()
            .id(id)
            .title(title)
            .content(content)
            .price(price)
            .scope(scope)
            .boardType(gym)
            .build();
    }

}