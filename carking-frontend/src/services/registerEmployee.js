import api from "./api";

export default async function registerEmployee(
  name,
  username,
  password,
  cpf,
  cellnumber,
  type
) {
  try {
    const response = await api().post("/employee", {
      name,
      username,
      password,
      cpf,
      cellnumber,
      role: type
    });

    return response.data.id;
  } catch (err) {
    if (err.response) {
      if (err.response.status === 418) {
        //User exists
        return -1;
      }
    }
  }
}
