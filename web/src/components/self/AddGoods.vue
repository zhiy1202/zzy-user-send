<template>
  <div class="goods_container">
    <div class="goods_bar">
      <ul>
        <li>
          产品名:
          <el-input v-model="goods.goodsName" placeholder="请输入内容"></el-input>
        </li>
        <li>
          简介:
          <el-input v-model="goods.goodsIntro" placeholder="请输入内容"></el-input>
        </li>
        <li>
          价格:
          <el-input v-model="goods.goodsPrice" placeholder="请输入内容"></el-input>
        </li>
        <li>
          类型:
          <el-input v-model="goods.goodsType" placeholder="请输入内容"></el-input>
        </li>
        <li>
          是否是活动商品:
          <el-radio v-model="goods.goodsActice" label="1">是</el-radio>
          <el-radio v-model="goods.goodsActice" label="0">否</el-radio>
        </li>
        <li>
          <el-upload
            action=""
            class="upload-demo"
            multiple
            :http-request="uploadImg"
            :limit="12"
            :on-exceed="handleExceed"
            :before-upload="beforeHandler"
            :on-remove="removeHandler"
            :file-list="fileList">
            <el-button size="small" type="primary">展示图片</el-button>
          </el-upload>  
        </li>
        <li>
          <el-button type="success" @click="addGoods">添加产品</el-button>
        </li>

      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return{
      fileList:[],
      goods:{
        goodsName:'',
        goodsIntro:'',
        goodsPrice:0,
        goodsType:'',
        goodsActice:1
      }
    }
  },
  methods:{
    beforeHandler(file){
      this.fileList.push(file)
    },
    removeHandler(file){
      let index = this.fileList.findIndex(fileItem=>fileItem.uid===file.uid);
      this.fileList.splice(index,1);
    },
    uploadImg(option){
      console.log(option);
    },
    handleExceed(){
      alert("最大支持12张图片")
    },
    addGoods(){
      //返回goods id 
      console.log(this.goods);
      // console.log(this.fileList);
      let that = this
      let formData = new FormData()
      formData.append('goodsName', this.goods.goodsName)
      formData.append('goodsIntro', this.goods.goodsIntro)
      formData.append('goodsPrice', this.goods.goodsPrice)
      formData.append('goodsType', this.goods.goodsType)
      formData.append('goodsActice', this.goods.goodsActice)
      this.$axios.post('/goods/addGoods',formData).then(res=>{
        let goodsId = res.data.data
        console.log(goodsId);
        console.log(that.fileList);
        let formData = new FormData()
        this.fileList.forEach((item)=>{
            formData.append("files",item);
        })
        formData.append('goodsId',goodsId)
        this.$axios.post('/goodsImgs/upload', formData,{headers: {'Content-Type': 'multipart/form-data'}}).then(res=>{
          if(res.data.data){
            alert("上传成功")
          }
        })
      })
    }
  }
}
</script>

<style scoped>
.goods_container{
  width: 100%;
}
.goods_bar{
  width: 80%;
  margin: 0 auto;
}
</style>