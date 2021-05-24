export const urlService = {
  getAll,
  getByCode,
  postUrl,
};

function getAll() {
  const requestOptions = {
    method: "GET",
  };
  return fetch("/api/", requestOptions).then(handleResponse);
}

function getByCode(code) {
  const requestOptions = {
    method: "GET",
  };
  return fetch("/api/shorten/" + code, requestOptions).then(handleResponse);
}

function postUrl(longUrl) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: longUrl,
  };
  return fetch("/api/shorten/", requestOptions).then(handleResponse);
}

function handleResponse(response) {
  return response.json().then((data) => {
    if (!response.ok) {
      const error = (data && data.message) || response.statusText;
      return Promise.reject(error);
    }
    return data;
  });
}
