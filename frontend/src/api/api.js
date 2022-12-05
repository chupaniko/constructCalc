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
