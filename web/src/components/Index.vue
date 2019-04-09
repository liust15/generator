<template>
    <div id="index" v-cloak>
        <el-row>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <DataForm></DataForm>
                </div>
            </el-col>
            <el-col :span="18">
                <div class="grid-content bg-purple-light">
                    <DataDisplay></DataDisplay>
                </div>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    import DataForm from './indexComponent/DataForm'
    import DataDisplay from './indexComponent/DataDisplay'

    export default {
        name: "Index",
        components: {
            DataForm,
            DataDisplay
        }
    }
</script>

<style scoped>

</style>