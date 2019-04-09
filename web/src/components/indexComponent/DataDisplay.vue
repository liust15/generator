<template>
    <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="Domain" name="Domain">Domain</el-tab-pane>
            <el-tab-pane label="ServiceImpl" name="ServiceImpl">ServiceImpl</el-tab-pane>
            <el-tab-pane label="Service" name="Service">Service</el-tab-pane>
            <el-tab-pane label="Mapper" name="Mapper">Mapper</el-tab-pane>
            <el-tab-pane label="MapperXml" name="MapperXml">MapperXml</el-tab-pane>
        </el-tabs>
        <div v-highlight style="text-align: left;">
            <pre>
                <code v-if="isXml" v-html="display"></code><code v-else v-text="display"></code>
            </pre>
        </div>
        <div></div>
    </div>
</template>
<script>
    import eventVue from '../../../plugins/eventVue.js'

    export default {
        data() {
            return {
                activeName: 'second',
                TableFileInfo: {
                    domain: 'domain',
                    mapperInter: 'mapperInter',
                    service: 'service',
                    serviceImpl: 'serviceImpl',
                    mapperXml: 'mapperXml'
                },
                display: "",
                isXml: false
            };
        },
        created: function () {
            this.getTableInfo();
        },
        methods: {
            getTableInfo: function () {
                eventVue.$on("tableInfo", (message) => {
                    this.TableFileInfo = message;
                    this.display = this.TableFileInfo.domain
                })
            },
            handleClick(tab) {
                this.isXml = false;
                switch (tab.name) {
                    case "Domain":
                        this.display = this.TableFileInfo.domain;
                        break;
                    case "Mapper":
                        this.display = this.TableFileInfo.mapperInter;
                        break;
                    case "Service":
                        this.display = this.TableFileInfo.service;
                        break;
                    case "ServiceImpl":
                        this.display = this.TableFileInfo.serviceImpl;
                        break;
                    case "MapperXml":
                        this.display = this.TableFileInfo.mapperXml;
                        this.isXml = false;
                        break;
                }
            }
        }
    }
</script>

<style scoped>

</style>