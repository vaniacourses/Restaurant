package my.restaurant.modal;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import my.restaurant.dto.CurrencyDTO;
import my.restaurant.dto.PriceDTO;
import my.restaurant.dto.ProductDTO;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testAddItem() {
        
        UUID productId = UUID.randomUUID();
        PriceDTO price = new PriceDTO(10, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO = new ProductDTO(productId, "Produto Teste", "/img/product/test.jpg", "Descrição do produto", price, null);
        
        shoppingCart.addItem(productDTO, 1);
        
        Assertions.assertFalse(shoppingCart.isEmpty());
        Assertions.assertEquals(1, shoppingCart.getCount());
    
    }

    @Test
    public void testRemoveItem() {
        
        UUID productId = UUID.randomUUID();
        PriceDTO price = new PriceDTO(10, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO = new ProductDTO(productId, "Produto Teste", "/img/product/test.jpg", "Descrição do produto", price, null);
        
        shoppingCart.addItem(productDTO, 1);
        shoppingCart.removeItem(productDTO);
        
        Assertions.assertTrue(shoppingCart.isEmpty());
    
    }

    @Test
    public void testUpdateQuantity() { // teste para verificar se a atualização do carrinho está funcionando corretamente
        
        UUID productId = UUID.randomUUID();
        PriceDTO price = new PriceDTO(5, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO = new ProductDTO(productId, "Produto Teste", "/img/product/test.jpg", "Descrição do produto", price, null);
        
        shoppingCart.addItem(productDTO, 1);
        
        shoppingCart.updateQuantity(productDTO, 3);
        
        Assertions.assertFalse(shoppingCart.isEmpty());
        Assertions.assertEquals(3, shoppingCart.getCount());
    
    }

    @Test
    public void testClearCart() { // verificar se o carrinho está sendo esvaziado corretamente
        
        UUID productId = UUID.randomUUID();
        PriceDTO price = new PriceDTO(10, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO = new ProductDTO(productId, "Produto Teste", "/img/product/test.jpg", "Descrição do produto", price, null);
        
        shoppingCart.addItem(productDTO, 1);
        shoppingCart.clear();
        
        Assertions.assertTrue(shoppingCart.isEmpty());
        Assertions.assertEquals(0, shoppingCart.getCount());
    
    }

    @Test
    public void testTotalPriceCalculation() { // verificar se o preço total está sendo calculado corretamente
        
        UUID productId1 = UUID.randomUUID();
        PriceDTO price1 = new PriceDTO(10, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO1 = new ProductDTO(productId1, "Produto 1", "/img/product/product1.jpg", "Descrição do produto 1", price1, null);
        
        UUID productId2 = UUID.randomUUID();
        PriceDTO price2 = new PriceDTO(5, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO2 = new ProductDTO(productId2, "Produto 2", "/img/product/product2.jpg", "Descrição do produto 2", price2, null);
        
        shoppingCart.addItem(productDTO1, 2);
        shoppingCart.addItem(productDTO2, 1);
        
        Assertions.assertEquals(25, shoppingCart.getPrice());
    
    }

    @Test
    public void testEmptyCartAfterRemoveAllItems() { // verificar se o carrinho fica vazio depois de remover todos os itens

        UUID productId = UUID.randomUUID();
        PriceDTO price = new PriceDTO(15, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO = new ProductDTO(productId, "Produto Teste", "/img/product/test.jpg", "Descrição do produto", price, null);
        
        shoppingCart.addItem(productDTO, 1);
        shoppingCart.removeItem(productDTO);
        
        Assertions.assertTrue(shoppingCart.isEmpty());
        Assertions.assertEquals(0, shoppingCart.getCount());
    }

    @Test
    public void testRemoveItemWithZeroQuantity() { // verificar se a atualização da quant. de um item para zero remove o item do carrinho

        UUID productId = UUID.randomUUID();
        PriceDTO price = new PriceDTO(20, new CurrencyDTO("Dólar Americano", "USD"));
        ProductDTO productDTO = new ProductDTO(productId, "Produto Teste", "/img/product/test.jpg", "Descrição do produto", price, null);
        
        shoppingCart.addItem(productDTO, 1);
        shoppingCart.updateQuantity(productDTO, 0);
        
        Assertions.assertTrue(shoppingCart.isEmpty());
        Assertions.assertEquals(0, shoppingCart.getCount());
    }

}
