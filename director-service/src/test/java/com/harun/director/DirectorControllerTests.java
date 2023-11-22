package com.harun.director;


import com.harun.director.service.IDirectorService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class DirectorControllerTests {

    @InjectMocks
    private final IDirectorService iDirectorService;

}
