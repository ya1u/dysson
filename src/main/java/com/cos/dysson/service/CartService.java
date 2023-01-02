package com.cos.dysson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.model.Cart;
import com.cos.dysson.model.CartItem;
import com.cos.dysson.model.Product;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.CartItemRepositoory;
import com.cos.dysson.repository.CartRepository;
import com.cos.dysson.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepositoory cartItemRepositoory;
	
	@Transactional
	public void addCart(Users users, Product newProduct, int amount) {
		
		Cart cart = cartRepository.findByUsersId(users.getId());
				
		if (cart == null) {
			cart = Cart.createCart(users);
			cartRepository.save(cart);
		}
		
		Product  product = productRepository.findProductById(newProduct.getId());
		CartItem cartItem = cartItemRepositoory.findByCartIdAndProductId(cart.getId(), product.getId());
		
		if (cartItem == null) {
			cartItem = CartItem.createCartItem(cart, product, amount);
			cartItemRepositoory.save(cartItem);
		}
		
		else {
			CartItem update = cartItem;
			update.setCart(cartItem.getCart());
			update.setProduct(cartItem.getProduct());
			update.addCount(amount);
			update.setCount(update.getCount());
			cartItemRepositoory.save(update);
		}
		
		cart.setCount(cart.getCount() + amount);
		
	}
	@Transactional
	public void 카트수정(Cart cart) {
		Cart persistance = cartRepository.findById(cart.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("카트 찾기 실패");
		});
		int count = cart.getCount();

		persistance.setCount(count);

		
		//회원수정 메서드종료 = 서비스종료  = 트랜잭션 종료= commit
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹 되어 update문 날림.
	}

	 @Transactional(readOnly = true)
	 public List<CartItem> allUserCartView(Cart userCart) {
		return cartItemRepositoory.findByCart(userCart);
	}

	@Transactional(readOnly = true)
	public CartItem findCartItemById(Integer itemId) {
		return cartItemRepositoory.findById(itemId).get();
	}

	public void cartItemDelete(Integer itemId) {
		cartItemRepositoory.deleteById(itemId);
	}

	public Cart findUserCart(Integer id) {
		return cartRepository.findByUsersId(id);
	}

	@Transactional
    public void plusCartCount(int userId) {
		Cart cart = cartRepository.findByUsersId(userId);

		int count = cart.getCount();

		cart.setCount(count+1);
    }

	@Transactional
	public void minusCartCount(int userId) {
		Cart cart = cartRepository.findByUsersId(userId);

		int count = cart.getCount();
		int cnt = count-1;

		if (cnt >= 1) {
			cart.setCount(cnt);
		} else {
			cart.setCount(1);
		}
	}

	@Transactional
	public void minusCount(Integer id, int cnt) {
		Cart cart = cartRepository.findByUsersId(id);

		cart.setCount(cnt);
	}

	public void allCartItemDelete(int id) {
		List<CartItem> cartItems = cartItemRepositoory.findAll();

		for (CartItem cartItem : cartItems) {
			if (cartItem.getCart().getUsers().getId() == id) {
				cartItem.getCart().setCount(0);
				cartItemRepositoory.deleteById(cartItem.getId());
			}
		}
	}
}