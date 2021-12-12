package com.gbl.crossjoin.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gbl.crossjoin.GblCrossjoinTestApplicationTests;
import com.gbl.crossjoin.repository.domain.Product;
import com.gbl.crossjoin.repository.domain.Store;
import com.gbl.crossjoin.service.ProductService;
import com.gbl.crossjoin.service.StoreService;

@RunWith(SpringRunner.class)
public class StoreApiTests extends GblCrossjoinTestApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private StoreService storeService;
	
	@MockBean
	private ProductService productService;
	
	private List<Store> mockedStoreList;
	private List<Product> mockedProductList;
	
	private static final ObjectMapper mapper = new ObjectMapper()
		    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
		    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		    .registerModule(new JavaTimeModule());
	
	@Before
	public void setup() {
		mockedStoreList = this.createMockStoreList();
		mockedProductList = this.createMockProductList();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void findAllTest() throws Exception {
		Mockito.when(storeService.findAll()).thenReturn(mockedStoreList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/stores").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
		
	}
	
	@Test
	public void findProductsTest() throws Exception {
		Mockito.when(productService.findByStore(Mockito.anyLong())).thenReturn(mockedProductList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/stores/"+ 1 + "/products").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
		
	}
	
	private List<Store> createMockStoreList() {
		List<Store> listStores = new ArrayList<Store>();
		Store store = new Store();
		store.setId(Long.valueOf(1));
		store.setName("Name");;
		store.setPhone("123456789");
		store.setEmail("email@email.com");
		store.setAddress("first avenue");
		store.setCity("City");
		listStores.add(store);
		return listStores;
	}
	
	private List<Product> createMockProductList() {
		List<Product> listProduct = new ArrayList<Product>();
		Product product = new Product();
		product.setId(Long.valueOf(1));
		product.setName("Name");;
		product.setBrand("Brand");
		product.setPrice(Double.valueOf(5));
		listProduct.add(product);
		return listProduct;
	}

	
}
