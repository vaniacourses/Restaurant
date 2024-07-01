package my.restaurant.modal;

import my.restaurant.dto.PriceDTO;
import my.restaurant.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShoppingCartTest {

    @InjectMocks
    private ShoppingCart carrinhoDeCompras;

    @Mock
    private ProductDTO produtoMock;

    @Mock
    private ProductDTO outroProdutoMock;

    @BeforeEach
    public void configurar() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testarAdicionarItem() {
        UUID idProduto = UUID.randomUUID();
        PriceDTO preco = new PriceDTO(10, null);
        when(produtoMock.productId()).thenReturn(idProduto);
        when(produtoMock.price()).thenReturn(preco);

        carrinhoDeCompras.addItem(produtoMock, 1);
        
        assertEquals(1, carrinhoDeCompras.getCount());
    }

    @Test
    public void testarAdicionarMultiplosItens() {
        UUID idProduto1 = UUID.randomUUID();
        UUID idProduto2 = UUID.randomUUID();
        PriceDTO preco1 = new PriceDTO(10, null);
        PriceDTO preco2 = new PriceDTO(5, null);
        when(produtoMock.productId()).thenReturn(idProduto1);
        when(outroProdutoMock.productId()).thenReturn(idProduto2);
        when(produtoMock.price()).thenReturn(preco1);
        when(outroProdutoMock.price()).thenReturn(preco2);

        carrinhoDeCompras.addItem(produtoMock, 2);
        carrinhoDeCompras.addItem(outroProdutoMock, 1);

        assertEquals(3, carrinhoDeCompras.getCount());
    }

    @Test
    public void testarAtualizarQuantidade() {
        UUID idProduto = UUID.randomUUID();
        PriceDTO preco = new PriceDTO(5, null);
        when(produtoMock.productId()).thenReturn(idProduto);
        when(produtoMock.price()).thenReturn(preco);

        carrinhoDeCompras.addItem(produtoMock, 1);

        carrinhoDeCompras.updateQuantity(produtoMock, 3);

        assertEquals(3, carrinhoDeCompras.getCount());
    }

    @Test
    public void testarRemoverItem() {
        UUID idProduto = UUID.randomUUID();
        PriceDTO preco = new PriceDTO(10, null);
        when(produtoMock.productId()).thenReturn(idProduto);
        when(produtoMock.price()).thenReturn(preco);

        carrinhoDeCompras.addItem(produtoMock, 1);

        carrinhoDeCompras.removeItem(produtoMock);

        assertEquals(0, carrinhoDeCompras.getCount());
        assertEquals(0, carrinhoDeCompras.getPrice());
    }

    @Test
    public void testarCalculoPrecoTotal() {
        UUID idProduto1 = UUID.randomUUID();
        UUID idProduto2 = UUID.randomUUID();
        PriceDTO preco1 = new PriceDTO(10, null);
        PriceDTO preco2 = new PriceDTO(5, null);
        when(produtoMock.productId()).thenReturn(idProduto1);
        when(outroProdutoMock.productId()).thenReturn(idProduto2);
        when(produtoMock.price()).thenReturn(preco1);
        when(outroProdutoMock.price()).thenReturn(preco2);

        carrinhoDeCompras.addItem(produtoMock, 2);
        carrinhoDeCompras.addItem(outroProdutoMock, 1);

        assertEquals(25, carrinhoDeCompras.getPrice());
    }

    @Test
    public void testarLimparCarrinho() {
        UUID idProduto = UUID.randomUUID();
        PriceDTO preco = new PriceDTO(10, null);
        when(produtoMock.productId()).thenReturn(idProduto);
        when(produtoMock.price()).thenReturn(preco);

        carrinhoDeCompras.addItem(produtoMock, 1);

        carrinhoDeCompras.clear();

        assertEquals(0, carrinhoDeCompras.getCount());
        assertEquals(0, carrinhoDeCompras.getPrice());
        assertEquals(true, carrinhoDeCompras.isEmpty());
    }
}
