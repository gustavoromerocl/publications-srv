// package com.example.controller;

// import static org.mockito.Mockito.when;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import com.example.publicationssrv.controller.PublicationController;
// import com.example.publicationssrv.model.Publication;
// import com.example.publicationssrv.service.PublicationServiceImp;


// @WebMvcTest(PublicationController.class)
// public class PublicationControllerTest {
//   @Autowired
//   private MockMvc mockMvc;

//   @MockBean
//   private PublicationServiceImp publicationServiceMock;

//   @Test
//   public void getAllTest() throws Exception {
//     Publication publication1 = new Publication();
//     publication1.setTitle("Título 1");
//     Publication publication2 = new Publication();
//     publication1.setTitle("Título 2");

//     List<Publication> publications = Arrays.asList(publication1, publication2);
//     when(publicationServiceMock.getAllPublications()).thenReturn(publications);

//     mockMvc.perform(MockMvcRequestBuilders.get("/students"))
//       .andExpect(MockMvcResultMatchers.status().isOk());
//   }
// }
