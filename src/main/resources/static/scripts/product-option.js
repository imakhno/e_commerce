document.addEventListener("DOMContentLoaded", function () {
    const sizeSelect = document.getElementById("sizes");
    const colorSelect = document.getElementById("colors");
    const priceElement = document.getElementById("price");
    const oldPriceElement = document.getElementById("oldPrice");
    const categoryIdItem = document.getElementById("categoryIdUrl");
    const productIdItem = document.getElementById("productIdUrl");
    const productStock = document.getElementById("for-stock");
    const btnToCart = document.getElementById("btn-toCart");


    function updatePrice() {
        const sizeId = sizeSelect.value;
        const colorId = colorSelect.value;

        const categoryId = categoryIdItem.value;
        const productId = productIdItem.value;

        // Проверка наличия значений
        if (!categoryId || !productId) {
            console.error("categoryId или productId отсутствуют.");
            return;
        }
        // Проверка, что оба значения выбраны
        if (sizeId && colorId) {
            const url = `/categories/${categoryId}/${productId}/price?size_id=${sizeId}&color_id=${colorId}`;

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    if (data && data.price !== undefined) {
                        oldPriceElement.textContent =
                            (Math.ceil(data.price + (data.price * 0.15))).toLocaleString('ru-RU') + ' руб.';
                        priceElement.textContent = data.price.toLocaleString('ru-RU') + ' руб.';
                        if (data.stock < 1) {
                            productStock.textContent = 'Нет в наличии';
                            btnToCart.disabled = true;
                            btnToCart.style.backgroundColor = "#999999"
                            btnToCart.style.cursor = "not-allowed";
                        } else {
                            productStock.textContent = 'Есть в наличии';
                            btnToCart.style.backgroundColor = "#ed1651"
                            btnToCart.style.cursor = "pointer";
                            btnToCart.disabled = false;
                        }
                    } else {
                        console.error("Не удалось получить цену.");
                    }
                })
                .catch(error => console.error('Ошибка:', error));
        }
    }

    sizeSelect.addEventListener("change", updatePrice);
    colorSelect.addEventListener("change", updatePrice);


    updatePrice();
});

