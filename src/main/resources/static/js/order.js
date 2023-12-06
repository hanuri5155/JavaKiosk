let cart = [];

document.getElementById('productList').addEventListener('click', e => {
    const target = e.target;

    if(target.tagName !== 'BUTTON') return;

    // 장바구니 버튼 상태

    const isinCart = target.classList.contains('inCart');

    const tr = target.closest('tr');
    const productId = tr.getElementsByClassName('productId')[0].innerText;
    const productName = tr.getElementsByClassName('productName')[0].innerText;
    const productPrice = tr.getElementsByClassName('productPrice')[0].innerText;
    const quantityInput = document.getElementById("quantity_" + productId);

    const quantity = parseInt(quantityInput.value);

    const totalPrice = document.getElementById('totalPrice');
    let currentPrice = parseInt(totalPrice.innerText);

    if(isinCart){
        currentPrice -= parseInt(productPrice) * quantity;
        target.innerText = '장바구니 담기';
        target.classList.remove('inCart');
        quantityInput.disabled = false;
    }else{
        currentPrice += parseInt(productPrice) * quantity;
        target.innerText = '취소하기';
        target.classList.add('inCart');
        quantityInput.disabled = true;
    }
    
    totalPrice.innerText = String(currentPrice);
    

    cart.push({productId, productName, productPrice, quantity});
});

document.getElementById('order').addEventListener('click', e => {
    makeOrder();
});

function makeOrder(){
    fetch('/order', {
        method: 'POST',
        body: JSON.stringify(cart)
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            console.log(data);
            cart = [];
        })
}