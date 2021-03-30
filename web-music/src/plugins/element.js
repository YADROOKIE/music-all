import Vue from 'vue'
import {
    Button, Row, Col, Input, Avatar,
    Menu, MenuItem, Carousel, CarouselItem,
    Container, Aside, Main, Table, TableColumn,
    Pagination, Popover, Footer, Drawer, MessageBox, Message
} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// collapse 展开折叠
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';


Vue.use(Button)
Vue.use(Row)
Vue.use(Col)
Vue.use(Input)
Vue.use(Avatar)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Container)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Pagination)
Vue.use(Popover)
Vue.use(Footer)
Vue.use(Drawer)
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
Vue.component(CollapseTransition.name, CollapseTransition)






