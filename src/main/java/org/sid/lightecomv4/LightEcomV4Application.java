package org.sid.lightecomv4;

import java.util.Random;


import org.sid.lightecomv4.dao.CategoryRepository;
import org.sid.lightecomv4.dao.ProductRepository;
import org.sid.lightecomv4.entities.Category;
import org.sid.lightecomv4.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class LightEcomV4Application implements CommandLineRunner{
@Autowired
private ProductRepository productRepository;
@Autowired
private CategoryRepository categoryRepository;
@Autowired
private RepositoryRestConfiguration repositoryRestConfiguration; 

	public static void main(String[] args) {
		SpringApplication.run(LightEcomV4Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
		Category c1=categoryRepository.save(new Category(null,"computer",null,null,null));
		Category c2=categoryRepository.save(new Category(null,"imprimante",null,null,null));

		Category c3=categoryRepository.save(new Category(null,"Smart phones",null,null,null));
		Random rnd=new Random();
		
		categoryRepository.findAll().forEach(c->{
		for(int i=0;i<10;i++) {
			 Product p=new Product();
			// p.setId(null);
			 p.setName(RandomString.make(18));
			 p.setCurrentPrice(100+rnd.nextInt(10000));
			 p.setAvailable(rnd.nextBoolean());
			 p.setPromotion(rnd.nextBoolean());
			 p.setSelected(rnd.nextBoolean());
			 p.setCategory(c);
			 p.setPhotoName("unknown.png");
			 productRepository.save(p);}
			 });
	}
	
}
