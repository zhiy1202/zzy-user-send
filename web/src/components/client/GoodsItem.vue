<template>
  <div>
    <!-- 图片 -->
    <div class="img_size">
        <img :src="goodsImg" alt="">
    </div>
    <!-- 介绍 -->
    <div>
        <!-- 简介 -->
        <p>
            {{goodsName}}
        </p>
        <p class="intro_size">
            {{goodsIntro}}
        </p>
        <p class="price_size">
            ￥ {{goodsPrice}}
        </p>
    </div>
  </div>
</template>

<script>
export default {
    props:['goodsName','goodsPrice','goodsIntro','goodsImg']
}
</script>

<style scoped>
img{
    width: 100%;
    height: 100%;
}
.img_size{
    width: 90%;
    height: 240px;
}
.intro_size{
    width: 100%;
    height: 60px;
    overflow: hidden;
    margin-bottom: 8px;
}
.price_size{
    width: 100%;
    height: 20px;
}
</style>