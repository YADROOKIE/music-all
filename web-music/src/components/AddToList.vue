<template>
  <div >
    <el-button @click="dialogFormVisible = true">加入歌单</el-button>
    <el-dialog title="选择加入歌单" :visible.sync="dialogFormVisible">
          <el-table
            :data="list"
            stripe
            style="width: 100%">
            <el-table-column
              prop="id"
              label="ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="listName"
              label="歌单名称"
              width="180">
            </el-table-column>
            <el-table-column
              prop="createAt"
              label="创建时间">
            </el-table-column>
            <el-table-column
              prop="description"
              label="描述">
            </el-table-column>

            <el-table-column
              fixed="right"
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row.id)"  >加入歌单</el-button>
              </template>
            </el-table-column>
          </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>
  </div>
</template>


<script>
import {addUserSongList ,getUserSongList,addSongToList} from '../network/userSongList.js'

export default {
  props:{
    mId:{
      type: Number,
      require: true
    }
  },
  data() {
    return {    
      user:null,
      list:[],
      dialogFormVisible: false,
      form:{}
    };
  },
  created(){
    var userStr = sessionStorage.getItem('user')
    if(userStr!=null && userStr!=''){
      this.user = JSON.parse(userStr)
      this.loadList()
    }
  },
  methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      loadList(){
        getUserSongList(this.user.id).then(res=>{
          console.log(res)
          this.list = res.data.data.list 
        })
      },
      handleClick(id){
        var form = {musicId:this.mId,listId:id}
        addSongToList(form).then(res=>{
          if(res.data.code===20000){
            this.$message({
                    message: "添加成功",
                    type: "success"
              });
          }else{
            this.$message({
                    message: res.data.message,
                    type: "danger"
            });
          }

        })
      }
  }
};
</script>