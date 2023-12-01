setInterval(function () {
    location.reload();
}, 4000); // 4000 밀리초 = 4초

const table = document.getElementById('orderTable');
let cancelBtn = document.getElementsByClassName('.cancel');

table.addEventListener('click', e => {
    const target = e.target;
    if(target.tagName !== 'BUTTON' || !target.classList.contains("cancel")) return;

    const orderId = target.parentNode.parentNode.getElementsByClassName('orderId')[0].innerText;

    console.log(orderId);
    fetch('/order/cancel', {
        method: 'POST',
        body: orderId
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            console.log(data);
        })
})