<template>
    <div>
        <el-table
            :data="list"
            border
            style="width: 100%">
            <el-table-column label='序号' width="120">
                <template slot-scope="scope">
                    {{scope.$index+1}}
                </template>
                </el-table-column>
            <el-table-column
                prop="musicName"
                label="名称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="singer"
                label="歌手"
                width="120">
            </el-table-column>
            <el-table-column
                prop="type"
                label="类型"
                width="120">
            </el-table-column>

            <el-table-column
                prop="createAt"
                label="创建时间"
                width="240">
            </el-table-column>
            <el-table-column
                label="封面"
                width="120">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row.cover)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>

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
import {listMusic,delMusic} from '@/api/music'
export default {
    created(){
        this.loadData()
    },
    data(){
        return{
            list:[]
        }
    },
    methods:{
        loadData(){
            listMusic().then(res=>{
                this.list = res.data.list
            })
        },
         handleClick(url){
            var imgHtml = `<img src=http://localhost:8080/${url} height="300px" width="300px"  />`
            this.$alert(imgHtml, '查看', {
                dangerouslyUseHTMLString: true
            });
        },
        unpass(rid){
                this.$confirm('是否删除', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    }).then(({ value }) => {
                        console.log(value)
                        delMusic(rid).then( res => {
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