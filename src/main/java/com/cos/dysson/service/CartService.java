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
		cart.setCount(count-1);
	}

	@Transactional
	public void minusCount(Integer id, int cnt) {
		Cart cart = cartRepository.findByUsersId(id);

		cart.setCount(cnt);
	}
}