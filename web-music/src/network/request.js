import axios from 'axios'

let ajaxTimer = 0
export function request(config) {
    const instance = axios.create({
        baseURL: 'http://localhost:8080',
        timeOut: 5000
    });

    instance.interceptors.request.use(data => {
        ajaxTimer++;
        return data;
    }, err => {
        return err
    });

    instance.interceptors.response.use(data => {
        ajaxTimer--;
        return data;
    }, err => {
        return err;
    });
    return instance(config);
}

