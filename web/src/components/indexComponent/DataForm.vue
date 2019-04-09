<template>
    <div id="index" v-cloak>
        <el-container>
            <el-form ref="form" :model="sizeForm" label-width="80px">
                <el-form-item label="组名">
                    <el-input v-model="sizeForm.dbConfigModel.group"></el-input>
                </el-form-item>
                <el-form-item label="ip地址">
                    <el-input v-model="sizeForm.dbConfigModel.ip"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="sizeForm.dbConfigModel.user"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="sizeForm.dbConfigModel.password"
                              autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="数据库">
                    <el-select v-model="sizeForm.dbConfigModel.dbName" placeholder="请选择数据库"
                               @click.native="getDBNames">
                        <el-option v-for="dbName in dbNames" :key="dbName.dbName"
                                   :label="dbName.dbName"
                                   :value="dbName.dbName"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="数据表">
                    <el-select id="table" v-model="sizeForm.tableModel" placeholder="请选择数据表"
                               @click.native="getTables" @change="getTableInfo">
                        <el-option v-for="table in tables" :key="table.tableName"
                                   :label="table.tableName"
                                   :value="table"></el-option>
                    </el-select>
                </el-form-item>
                <el-row>
                    <el-button type="primary" @click="downloads(1)">全表生成</el-button>
                    <el-button type="primary" @click="downloads(0)">单表生成</el-button>
                </el-row>
            </el-form>
        </el-container>
    </div>
</template>

<script>

    import eventVue from '../../../plugins/eventVue.js'

    export default {
        data() {
            return {
                sizeForm: {
                    dbConfigModel: {
                        ip: '127.0.0.1:3306',
                        user: 'root',
                        password: 'root5515',
                        group: 'com.liust',
                        dbName: ''
                    }, tableModel: {
                        tableName: '',
                        tableComment: '',
                    }, attributes: 0
                },
                dbNames: [],
                tables: [],
                TableFileInfo: {
                    domain: '',
                    mapperInter: '',
                    server: '',
                    serverImpl: '',
                    mapperXml: ''
                },
            };
        },
        methods: {
            getDBNames() {
                let url = 'http://127.0.0.1:19004/show/database';
                const that = this;
                if (that.dbNames) {
                    this.$axios.post(url, JSON.stringify(that.sizeForm.dbConfigModel), {headers: {'Content-Type': 'application/json;charset=UTF-8'}}).then(function (response) {
                        that.dbNames = response.data.data;
                        that.sizeForm.tableModel.tableName = '';
                    }).catch(function (error) {
                        that.$notify.error({title: '错误', message: error});
                    });
                }
            },
            getTables() {
                let url = 'http://127.0.0.1:19004/show/tables';
                const that = this;
                if (that.sizeForm.dbConfigModel.dbName.length === 0) {
                    that.$notify.warning({
                        title: '警告',
                        message: '请选择一个数据库'
                    });
                } else {
                    if (that.tables) {
                        this.$axios.post(url, JSON.stringify(that.sizeForm.dbConfigModel), {
                            headers: {'Content-Type': 'application/json;charset=UTF-8'}
                        }).then(function (response) {
                            that.tables = response.data.data;
                        }).catch(function (error) {
                            window.console.log(error);
                        });
                    }
                }
            }, downloads(attributes) {
                let url = 'http://127.0.0.1:19004/download/table';
                const that = this;
                if (that.sizeForm.dbConfigModel.dbName.length === 0) {
                    that.$notify.warning({title: '警告', message: '请选择一个数据库'});
                } else {
                    if (attributes === 0&&that.sizeForm.tableModel.tableName.length===0) {
                        that.$notify.warning({title: '警告', message: '请选择需要生成的表'});
                        return;
                    }else {
                        that.sizeForm.tableModel.tableName=" ";
                        that.sizeForm.tableModel.tableComment=" ";
                    }
                    that.sizeForm.attributes = attributes;
                    this.$axios({
                        url: url,
                        method: 'post',
                        data: JSON.stringify(that.sizeForm),
                        responseType: 'blob'     //接收类型设置，否者返回字符型
                    }).then(res => {           //定义文件名等相关信息
                        const blob = res.data
                        const reader = new FileReader()
                        reader.readAsDataURL(blob)
                        reader.onload = (e) => {
                            const a = document.createElement('a')
                            a.download = that.sizeForm.dbConfigModel.dbName + '.zip';
                            a.href = e.target.result;
                            document.body.appendChild(a)
                            a.click()
                            document.body.removeChild(a)
                        }
                    })
                    that.sizeForm.tableModel.tableName='';
                    that.sizeForm.tableModel.tableComment='';
                }
            }, // 下载文件
            download(data) {
                if (!data) {
                    return
                }
                let url = window.URL.createObjectURL(new Blob([data]))
                let link = document.createElement('a')
                link.style.display = 'none';
                link.href = url
                link.setAttribute('download', 'excel.zip')
                document.body.appendChild(link)
                link.click()
            }
            ,
            getTableInfo() {
                let url = 'http://127.0.0.1:19004/show/domain';
                const that = this;
                this.$axios.post(url, JSON.stringify(that.sizeForm))
                    .then(function (response) {
                        that.TableFileInfo = response.data.data;
                        eventVue.$emit("tableInfo", that.TableFileInfo)   //$emit这个方法会触发一个事件
                    }).catch(function (error) {
                    window.console.log(error);
                });
            }
        }
    }
    ;
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
