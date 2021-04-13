<template>
    <div>
          <el-table
            :data="listData"
            border
            style="width: 100%">
            <el-table-column label='序号' width="120">
                <template slot-scope="scope">
                    {{scope.$index+1}}
                </template>
                </el-table-column>
            <el-table-column
                prop="name"
                label="名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="age"
                label="年龄"
                width="120">
            </el-table-column>
            <el-table-column
                prop="gender"
                label="性别"
                width="120">
            </el-table-column>
            <el-table-column
                prop="stuNo"
                label="学号"
                width="180">
            </el-table-column>
            <el-table-column
                prop="createAt"
                label="创建时间"
                width="120">
            </el-table-column>
            <!-- <el-table-column
                label="学生证图片"
                width="180">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row.stuNoUrl)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column> -->

                <el-table-column
                    label="操作"
                   >
                    <template slot-scope="scope">
                        <el-button type="success" @click="pass(scope.row.requestId)" size="small">通过审核</el-button>
                        <el-button type="warning"  @click="unpass(scope.row.id)" size="small" >删除</el-button>
                    </template>
                </el-table-column>
        </el-table>
    </div>
</template>

<script>
import {list,pass,unpass} from '@/api/certify'
import {listUser,delUser} from  '@/api/user'
import {formatTime} from '@/utils/index'
export default {
    data(){
        return {
            listData:[]
        }
    },
    created(){
        this.loadData()
    },
    methods:{
        loadData(){
            listUser().then( res => {
                console.log(res)

                this.listData = res.data.list
                for(var i=0;i<this.listData.length;i++){
                    var d = new Date(this.listData[i].createAt)
                    this.listData[i].createAt = formatTime(d)
                }
            })

        },
        handleClick(url){
            var imgHtml = `<img src=${url} height="300px" width="300px"  />`
            this.$alert(imgHtml, '查看', {
                dangerouslyUseHTMLString: true
            });
        },
        pass(rid){
            pass(rid).then( res => {
                console.log(res)
                this.loadData()
            })
        },
        unpass(rid){
                this.$confirm('是否删除', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    }).then(({ value }) => {
                        console.log(value)
                        delUser(rid).then( res => {
                            console.log(res)
                            this.loadData()
                        })
                        this.$message({
                            type: 'success',
                            message: '删除成功'
                        });
                    }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消'
                    });       
                });
        }
    }
}
</script>