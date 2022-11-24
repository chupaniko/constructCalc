import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:6579/api/",
});

export const apiGetClients = () => {
  return instance.get("/clients/all");
};

export const apiCreateClient = (data) => {
  return instance.post("/clients/save", data);
};

export const apiGetClient = (id) => {
  return instance.get(`/clients/byId/${id}`);
};

export const apiAuth = (data) => {
  return instance.post("/authentication", data);
}

export const headers = {
  'Content-Type': 'application/json;charset=utf-8',
};
