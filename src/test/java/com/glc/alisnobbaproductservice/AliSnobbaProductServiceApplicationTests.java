package com.glc.alisnobbaproductservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

// import static org.hamcrest.CoreMatchers.is;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.BDDMockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
// import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.alisnobbaproductservice.Controller.ProductController;
import com.glc.alisnobbaproductservice.Model.ProductModel;
import com.glc.alisnobbaproductservice.Repository.IProductRepository;

@SpringBootTest
class AliSnobbaProductServiceApplicationTests {
	@Mock
	private IProductRepository productRepository;
	@InjectMocks
	private ProductController productController;
	private MockMvc mvc;
	private JacksonTester<List<ProductModel>> jsonProducts;
	private JacksonTester<ProductModel> jsonProduct;

	// @Autowired
	// private ObjectMapper objectMapper;

	// @Autowired
	// private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testProductID() {
		ProductModel productModel = new ProductModel();
		Long id = 1L;
		productModel.setId(id);
		assertEquals(id, productModel.getId());
	}

	@Test
	public void builderProductModel() {
		Long id = 1L;
		String productName = "Ruby Slippers";
		String productImage = "Image";
		Long productPrice = 684750000L;
		String shortDescription = "An impressive pair of slippers featuring thousands of real rubies.";
		String longDescription = "Harry Winston has carefully crafted these fantastic shoes.  Each pair boasts a total of 4,600 gemstones including 1,350 carats of premium rubies and 50 carats of diamonds. You’ll be the talk of the town when you wear these slippers (not to mention the target of shoe thieves everywhere).  Harry makes no promise about how comfortable these slippers are though.";
		ProductModel productModel = ProductModel.builder()
				.id(id)
				.productName(productName)
				.productImage(productImage)
				.productPrice(productPrice)
				.shortDescription(shortDescription)
				.longDescription(longDescription)
				.build();
		assertEquals(id, productModel.getId());
		assertEquals(productName, productModel.getProductName());
		assertEquals(productImage, productModel.getProductImage());
		assertEquals(productPrice, productModel.getProductPrice());
		assertEquals(shortDescription, productModel.getShortDescription());
		assertEquals(longDescription, productModel.getLongDescription());
	}

	@Test
	public void canGetAndSetData() throws Exception {
		String productName = "Ruby Slippers";
		String shortDescription = "An impressive pair of slippers featuring thousands of real rubies.";
		String longDescription = "Harry Winston has carefully crafted these fantastic shoes.  Each pair boasts a total of 4,600 gemstones including 1,350 carats of premium rubies and 50 carats of diamonds. You’ll be the talk of the town when you wear these slippers (not to mention the target of shoe thieves everywhere).  Harry makes no promise about how comfortable these slippers are though.";
		String productImage = "Image";
		Long productPrice = 12012000L;
		ProductModel product = new ProductModel(productName, shortDescription, longDescription, productImage,
				productPrice);
		assertEquals(productName, product.getProductName());
		assertEquals(shortDescription, product.getShortDescription());
		assertEquals(longDescription, product.getLongDescription());
		assertEquals(productImage, product.getProductImage());
		assertEquals(productPrice, product.getProductPrice());
	}

	@Test
	public void canGetAllProducts() throws Exception {
		ProductModel productModel1 = new ProductModel("Ruby Slippers",
				"An impressive pair of slippers featuring thousands of real rubies.",
				"Harry Winston has carefully crafted these fantastic shoes.  Each pair boasts a total of 4,600 gemstones including 1,350 carats of premium rubies and 50 carats of diamonds. You’ll be the talk of the town when you wear these slippers (not to mention the target of shoe thieves everywhere).  Harry makes no promise about how comfortable these slippers are though.",
				"https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg",
				684750000L);
		ProductModel productModel2 = new ProductModel("Chocolate Pudding",
				"This better be the best pudding you ever ate!",
				"Marc Guibert of Lindeth Howe Country House Hotel in England's Lake District has hand crafted each of these delicious desserts especially for Alisnobba’s highly pampered customers, the chocolate pudding — made from high-end chocolate, gold, caviar, and, why not, a two-carat diamond — aims to be the world's most expensive dessert. It's also served in an edible replica of a Faberge egg.",
				"https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ChocolatePudding.png", 7988750L);
		ProductModel productModel3 = new ProductModel("Diamond Watch",
				"This intentionally outrageous time piece tells time just as well as the phone in your pocket!",
				"This gargantuan watch will certainly cover your whole wrist, and if you look really closely you can even see what time it is (although you may go blind from all those diamonds). If you ever fall on hard times, you can sell it and live comfortably on the interest for the rest of your life!",
				"https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/DiamondWatch.jpg", 4413104582L);
		ProductModel productModel4 = new ProductModel("Golden Toilet",
				"The perfect throne for our discerning billionaire customers!",
				"This right-height elongated Royal Solid Gold Toilet has a divine yet modern royal look. Its dual flush high-efficiency royal gold toilet uses just 3 litres of water per flush saving 50% more water than traditional toilets. It seems funny to spend so much money on a toilet and then worry about wasting a bit of water 🙂",
				"https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/GoldenToilet.jpg", 228578528L);
		ProductModel productModel5 = new ProductModel("Land Yacht Motor Home",
				"All the luxury of a yacht, but with wheels and a really weird wind screen!",
				"The Marchi Mobile EleMMent Palazzo is truly a castle on wheels. With a wall thickness of over 60 mm and a Sky lounge offering a full panoramic view, you can take this RV anywhere, from off road expeditions to amazing picturesque corners of the Earth. The top deck can be accessed via a stairwell. The interior has a 4 meter couch area, with bar, wine cabinet and ice maker access and a large TV screen and a kitchenette nearby. If that wasn’t enough, adjacent to the master bedroom there’s a spa area for light therapy, a rainfall shower and furniture with large storage capacity.",
				"https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/LandYachtMotorHome.jpg",
				685918691L);
		List<ProductModel> productList = new LinkedList<>();
		productList.add(productModel1);
		productList.add(productModel2);
		productList.add(productModel3);
		productList.add(productModel4);
		productList.add(productModel5);
		when(productRepository.findAll()).thenReturn(productList);
		mvc.perform(get("/product/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(jsonProducts.write(productList).getJson()));
	}

	@Test
	public void canGetProductById() throws Exception{
	ProductModel productModel1 = new ProductModel("Ruby Slippers","An impressivepair of slippers featuring thousands of real rubies.","Harry Winston hascarefully crafted these fantastic shoes. Each pair boasts a total of 4,600gemstones including 1,350 carats of premium rubies and 50 carats of diamonds.You’ll be the talk of the town when you wear these slippers (not to mentionthe target of shoe thieves everywhere). Harry makes no promise about howcomfortable these slippers arethough.","https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg",684750000L);
	when(productRepository.findById(1L)).thenReturn(Optional.of(productModel1));
	mvc.perform(get("/product/1").contentType(MediaType.APPLICATION_JSON)
	.content(jsonProduct.write(productModel1).getJson())).andExpect(status().isOk());
	}


	@Test
	public void canPostProduct() throws Exception {
		ProductModel productModel1 = new ProductModel("Ruby Slippers",
				"An impressive pair of slippers featuring thousands of real rubies.",
				"Harry Winston has carefully crafted these fantastic shoes.  Each pair boasts a total of 4,600 gemstones including 1,350 carats of premium rubies and 50 carats of diamonds. You’ll be the talk of the town when you wear these slippers (not to mention the target of shoe thieves everywhere).  Harry makes no promise about how comfortable these slippers are though.",
				"https://raw.githubusercontent.com/jeff-lent/Alisnobba/main/Capstone/ActualRubyRubySlippers.jpg",
				684750000L);
		mvc.perform(post("/product/add").contentType(MediaType.APPLICATION_JSON)
				.content(jsonProduct.write(productModel1).getJson())).andExpect(status().isOk());
	}

	@Test
	public void canGetACartWhenTheCartDoesntExist() throws Exception {
		Long id = 1L;
		doReturn(Optional.empty()).when(productRepository).findById(id);
		mvc.perform(MockMvcRequestBuilders.get("/product/{id}", id)).andExpect(status().isNotFound());
	}

}
