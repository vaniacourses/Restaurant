package my.restaurant.controller;

import my.restaurant.dto.ProductDTO;
import my.restaurant.modal.ShoppingCart;
import my.restaurant.modal.forms.ProductCartForm;
import my.restaurant.service.CartService;
import my.restaurant.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private ProductService servicoProduto;

    @Mock
    private CartService servicoCarrinho;

    @InjectMocks
    private CartController controladorCarrinho;

    @BeforeEach
    public void configurar() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testarPaginaCarrinho() {
        ShoppingCart carrinhoCompras = mock(ShoppingCart.class);
        Model modelo = mock(Model.class);
        when(servicoCarrinho.getCart()).thenReturn(carrinhoCompras);
        when(carrinhoCompras.getPrice()).thenReturn(100.0f);
        when(carrinhoCompras.getCurrency()).thenReturn("R$");
        when(carrinhoCompras.getTax()).thenReturn(10.0f);

        String resultado = controladorCarrinho.cartPage(modelo);

        assertEquals("cart", resultado);
        verify(modelo).addAttribute("items", carrinhoCompras.getCartItems());
        verify(modelo).addAttribute("price", carrinhoCompras.getPrice());
        verify(modelo).addAttribute("currency", carrinhoCompras.getCurrency());
        verify(modelo).addAttribute("tax", carrinhoCompras.getTax());
        verify(modelo).addAttribute(eq("productCartForm"), any(ProductCartForm.class));
    }

    @Test
    void testarAdicionarAoCarrinho() {
        ProductCartForm formularioProdutoCarrinho = new ProductCartForm();
        formularioProdutoCarrinho.setProductId(UUID.randomUUID());
        formularioProdutoCarrinho.setQuantity(2);

        ProductDTO produtoDTO = mock(ProductDTO.class);
        when(servicoProduto.getProduct(formularioProdutoCarrinho.getProductId())).thenReturn(produtoDTO);

        String resultado = controladorCarrinho.addToCart(formularioProdutoCarrinho);

        assertEquals("redirect:/cart", resultado);
        verify(servicoCarrinho).addItemToCart(produtoDTO, formularioProdutoCarrinho.getQuantity());
    }

    @Test
    void testarAtualizarCarrinho() {
        UUID idProduto = UUID.randomUUID();
        ProductCartForm formularioProdutoCarrinho = new ProductCartForm();
        formularioProdutoCarrinho.setProductId(idProduto);
        formularioProdutoCarrinho.setQuantity(0);

        RedirectAttributes atributosRedirecionamento = mock(RedirectAttributes.class);
        ProductDTO produtoDTO = mock(ProductDTO.class);
        when(servicoProduto.getProduct(idProduto)).thenReturn(produtoDTO);
        when(produtoDTO.name()).thenReturn("Produto Exemplo");

        String resultado = controladorCarrinho.updateCart(formularioProdutoCarrinho, atributosRedirecionamento);

        assertEquals("redirect:/cart", resultado);
        verify(servicoCarrinho).updateItem(produtoDTO, formularioProdutoCarrinho.getQuantity());
        verify(atributosRedirecionamento).addFlashAttribute("cartItemRemovedMsg", "Produto Exemplo was removed");
    }

    @Test
    void testarRemoverDoCarrinho() {
        RedirectAttributes atributosRedirecionamento = mock(RedirectAttributes.class);
        UUID idProduto = UUID.randomUUID();
        ProductDTO produtoDTO = mock(ProductDTO.class);
        when(servicoProduto.getProduct(idProduto)).thenReturn(produtoDTO);

        String resultado = controladorCarrinho.removeFromCart(idProduto, atributosRedirecionamento);

        assertEquals("redirect:/cart", resultado);
        verify(servicoCarrinho).removeItem(produtoDTO);
        verify(atributosRedirecionamento).addFlashAttribute(anyString(), anyString());
    }

    @Test
    void testarObterCarrinhoDeCompras() {
        ShoppingCart carrinhoCompras = mock(ShoppingCart.class);
        when(servicoCarrinho.getCart()).thenReturn(carrinhoCompras);

        ShoppingCart resultado = controladorCarrinho.getShoppingCart();

        assertEquals(carrinhoCompras, resultado);
    }

    @Test
    void testarRemoverItem() {
        UUID idProduto = UUID.randomUUID();
        ProductDTO produtoDTO = mock(ProductDTO.class);
        when(servicoProduto.getProduct(idProduto)).thenReturn(produtoDTO);

        controladorCarrinho.removeItem(idProduto);

        verify(servicoCarrinho).removeItem(produtoDTO);
    }

    @Test
    void testarAtualizarItem() {
        UUID idProduto = UUID.randomUUID();
        ProductDTO produtoDTO = mock(ProductDTO.class);
        when(servicoProduto.getProduct(idProduto)).thenReturn(produtoDTO);

        controladorCarrinho.updateItem(idProduto, 3);

        verify(servicoCarrinho).updateItem(produtoDTO, 3);
    }
}
