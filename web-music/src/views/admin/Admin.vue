<template>
    <div>
        管理员
        <el-button @click="dialogFormVisible = true">添加歌曲</el-button>
        <el-button @click="dialogRFormVisible = true">添加演出</el-button>
    <el-dialog title="添加歌曲" :visible.sync="dialogFormVisible" width="30%" center >
        
      <el-form :model="form">
        <el-form-item label="封面">
            <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl"  width="100px" height="100px" >
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
        </el-form-item>
        <el-form-item label="音乐文件" >
            <el-upload
                class="upload-demo"
                action="http://localhost:8080/file/upload"
                :on-success="success"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传MP3文件</div>
            </el-upload>
        </el-form-item>
        <el-form-item label="歌名" >
          <el-input v-model="form.musicName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型" >
          <el-input v-model="form.type" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="歌手" >
          <el-input v-model="form.singer" autocomplete="off" ></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMusic()">添加歌曲</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加演出" :visible.sync="dialogRFormVisible" width="30%" center >
      <el-form :model="rForm">
          <el-form-item label="封面">
            <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccessR"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrlR" :src="imageUrlR"  width="100px" height="100px" >
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
      </el-form-item>
        <el-form-item label="演出名称" >
          <el-input v-model="rForm.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTicket()">添加演出</el-button>
      </div>
    </el-dialog>
    </div>
</template>


<script>
import {adminAddMusic,adminAddTicket} from '../../network/admin.js'
export default {
    data(){
        return{
            dialogFormVisible:false,
            dialogRFormVisible:false,
            form:{},
            rForm:{},
            formLabelWidth: '120px',
            fileList:[],
            imageUrl:null,
            imageUrlR:null
        }
    },
    methods:{
        addMusic(){
            adminAddMusic(this.form).then(res=>{
                console.log(res)
                if(res.data.code==20000){
                  this.form = {}
                  this.imageUrl = null
                  this.dialogFormVisible = false
                  this.fileList = []
                  this.$message({
                    message: "创建成功",
                    type: "success"
                  });
                }
            })
        },
        addTicket(){
            adminAddTicket(this.rForm).then(res=>{
                console.log(res)
                if(res.data.code==20000){
                  this.rForm = {}
                  this.imageUrlR = null
                  this.dialogRFormVisible = false
                  this.$message({
                    message: "创建成功",
                    type: "success"
                  });
                }
            })
        },
      handleRemove(file, fileList) {
        this.form.filePath = null
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      success(response, file, fileList){
          console.log(response)
          console.log(response.path.slice(5))
          this.form.filePath = response.path.slice(5)
      },
      handleAvatarSuccess(res, file) {
        console.log(res)
        this.imageUrl = 'http://localhost:8080/'+res.path.slice(5)
        this.form.cover =  res.path.slice(5)
      },
      handleAvatarSuccessR(res, file) {
        console.log(res)
        this.imageUrlR = 'http://localhost:8080/'+res.path.slice(5)
        this.rForm.cover =  res.path.slice(5)
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    },
    
}   
</script>