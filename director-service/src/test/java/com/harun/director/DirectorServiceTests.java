package com.harun.director;

import com.harun.common.base.BaseTest;
import com.harun.director.mapper.MapperGenerator;
import com.harun.director.mapper.MapperGeneratorSingleton;
import com.harun.director.model.Director;
import com.harun.director.repository.DirectorRepository;
import com.harun.director.service.DirectorService;
import com.harun.directorserviceapi.dto.DirectorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTests extends BaseTest {

    @InjectMocks
    private DirectorService directorService;
    @Mock
    private DirectorRepository directorRepositoryMock;
    @Spy
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private Director director;
    private DirectorDTO directorDTO;

    @BeforeEach
    public void setupTestData() {
        director = Director.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();

        directorDTO = DirectorDTO.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();
    }

    @Test
    @DisplayName("JUnit test for save director operation")
    public void givenDirectorDTOObject_whenCreateDirector_thenReturnSavedDirectorDTO() {
        //Given
        doReturn(director).when(mapper).directorDTOToDirector(directorDTO);
        doReturn(director).when(directorRepositoryMock).save(director);

        //When
        DirectorDTO savedDirectorDTO = directorService.save(directorDTO);

        //Then
        assertNotNull(savedDirectorDTO);
        verify(directorRepositoryMock, times(1)).save(director);
    }

    @Test
    @DisplayName("JUnit test for get director update operation")
    public void givenUpdatedDirector_whenUpdateDirector_thenReturnUpdateDirectorObject() {
        // Given
        Director directorTwo = Director.builder()
                .withName("Ahmet")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();

        doReturn(Optional.of(director)).when(directorRepositoryMock).findById(directorDTO.getId());
        doReturn(directorTwo).when(mapper).updateDirectorFromDTO(directorDTO, director);
        doReturn(directorTwo).when(directorRepositoryMock).save(directorTwo);

        // When
        directorDTO.setName("Ahmet");
        DirectorDTO updatedDirector = directorService.update(directorDTO, directorDTO.getId());

        // Then
        assertNotNull(updatedDirector);
        assertEquals(directorDTO.getName(), updatedDirector.getName());
        verify(directorRepositoryMock, times(1)).findById(director.getId());
        verify(directorRepositoryMock, times(1)).save(directorTwo);
    }

    @Test
    @DisplayName("JUnit test for get Director By Id")
    public void givenDirectorId_whenGetDirectorById_thenReturnDirectorObject() {
        // Given
        doReturn(Optional.of(director)).when(directorRepositoryMock).findById(director.getId());

        // When
        DirectorDTO dto = directorService.get(director.getId());

        // Then
        assertNotNull(dto);
        verify(directorRepositoryMock, times(1)).findById(director.getId());
    }

    @Test
    @DisplayName("JUnit test for get DirectorDTO Page")
    void givenListOfDirectorDTOs_whenGetAllDirectorsDTO_thenReturnDirectorsDTOPage() {
        //Given
        List<Director> list = new ArrayList<>();

        Director directorTwo = Director.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();

        Director directorThree = Director.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();

        list.add(director);
        list.add(directorTwo);
        list.add(directorThree);
        final Page<Director> pageOfDirector = new PageImpl<>(list);

        doReturn(pageOfDirector).when(directorRepositoryMock).findAll(Pageable.unpaged());

        //When
        Page<DirectorDTO> drcPage = directorService.getAll(Pageable.unpaged());

        //Then
        assertEquals(3, drcPage.getTotalElements());
        verify(directorRepositoryMock, times(1)).findAll(Pageable.unpaged());
    }
}
