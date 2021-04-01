<template>
  <div >
    <h2 v-if="user==null">
      未登录请先登录
    </h2>
    <div v-else>
          <el-button @click="dialogVisible = true">创建歌单</el-button>
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
                <el-button @click="loadListMusic(scope.row.id)"  >查看歌曲</el-button>
                <el-button  >删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
              <el-form :model="form">
                <el-form-item label="歌单名称" label-width="120px">
                  <el-input v-model="form.listName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="歌单描述" label-width="120px">
                  <el-input v-model="form.description" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="createList()">确 定</el-button>
            </span>
          </el-dialog>
          
          <el-dialog title="所有歌曲" :visible.sync="dialogFormVisible">
          <el-table
            :data="songList"
            stripe
            style="width: 100%">
            <el-table-column
              prop="id"
              label="ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="musicName"
              label="歌区名称"
              width="180">
            </el-table-column>
            <el-table-column
              prop="singer"
              label="歌手">
            </el-table-column>

            <el-table-column
              fixed="right"
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-button @click="play(scope.row)"  >播放</el-button>
                <el-button @click="cancel(scope.row.id)"  >取消收藏</el-button>
              </template>
            </el-table-column>
          </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>
    </div>

  </div>
</template>





<script>
import {addUserSongList ,getUserSongList,getListAllMusic} from '../../network/userSongList.js'
export default {
  name: "CatList",
  components: {
  },
  created(){
    var userStr = sessionStorage.getItem('user')
    if(userStr!=null && userStr!=''){
      this.user = JSON.parse(userStr)
      this.loadList()
    }
  },
  data() {
    return {
      user:null,
      dialogFormVisible: false,
      dialogVisible:false,
      form:{},
      list:[],
      songList:[]
    };
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
      createList(){
        this.form.userId = this.user.id
        addUserSongList(this.form).then(res=>{
          console.log(res)
          if(res.data.code===20000){
              this.dialogVisible = false;
              this.$message({
                    message: "创建成功",
                    type: "success"
              });
              this.loadList()
          }else{
              this.$message({
                    message: "创建失败",
                    type: "danger"
              });
          }
        })
      },
      loadListMusic(id){
        getListAllMusic(id).then(res=>{
          console.log(res)
          this.songList = res.data.data.list
          this.dialogFormVisible = true
        })
      },
      play(item){
        this.$emit("play",item)
      },
      cancel(id){
        
      }
  }
};
</script>







<style scoped>

</style>