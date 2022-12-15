import axios from "axios";

export const headers = {
  "Content-Type": "application/json;charset=utf-8",
};

const instance = axios.create({
  baseURL: "http://localhost:6579/api/",
});

export const apiGetClients = () => {
  return instance.get("/clients/all");
};

export const apiCreateClient = (data) => {
  return instance.post("/clients/save", data, { headers });
};

export const apiGetClient = (id) => {
  return instance.get(`/clients/byId/${id}`);
};

export const apiAuth = (data) => {
  return instance.post("/authentication", data, { headers });
};

export const apiGetConcreteValues = () => {
  return instance.get("/calculation/getBetonValues");
};

export const apiGetBetonPilesValues = () => {
  return instance.get("/calculation/getBetonPilesValues");
};

export const apiGetFoundationCalculation = (data) => {
  return instance.post("/calculation/foundation", data, { headers });
};

export const apiGetFoundationByCalculation = (id) => {
  return instance.get(`/calculation/getFoundationByCalculation/${id}`);
};

export const apiGetFoundationResults = (id) => {
  return instance.get(`/calculation/getFoundationResults/${id}`);
};