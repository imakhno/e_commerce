$(document).ready(function () {
    $('.card__images-slider').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: '.card__images-slider-nav'
    });
    $('.card__images-slider-nav').slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        asNavFor: '.card__images-slider',
        prevArrow: '<div class="portfolio-left_arrow"><img src="/images/icons/left.png" alt="Назад"></div>',
        nextArrow: '<div class="portfolio-right_arrow"><img src="/images/icons/right.png" alt="Назад"></div>',
        centerMode: true,
        focusOnSelect: true
    });
});

