import { useEffect, useState } from "react";
import { urlService } from "../services/urlService";

const truncateString = (str, num = 50) =>
  str.length <= num ? str : str.slice(0, num) + "...";

function Home() {
  const [urls, setUrls] = useState([]);
  const [urlText, setUrlText] = useState("");
  const [validateUrl, setValidateUrl] = useState({
    isValid: true,
    msg: "",
  });

  function getUrls() {
    setValidateUrl({ isValid: true, msg: "" });
    setUrlText("");
    return urlService
      .getAll()
      .then((urls) => setUrls(urls))
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  useEffect(() => {
    getUrls();
  }, []);

  function getShortenUrl() {
    if (urlText.trim().length > 1) {
      urlService
        .postUrl(urlText)
        .then((d) => getUrls())
        .catch((error) => setValidateUrl({ isValid: false, msg: error }));
    } else {
      setValidateUrl({ isValid: false, msg: "Url Value cannot be blank" });
    }
  }

  const navbar = (
    <nav className="navbar navbar-dark bg-primary">
      <div className="container-fluid">
        <a className="navbar-brand" href="#">
          Url Shortener
        </a>
      </div>
    </nav>
  );

  const shortenForm = (
    <form className="">
      <div className="input-group mb-3">
        <input
          type="text"
          id="urlText"
          className={`form-control form-control-lg ${
            validateUrl.isValid ? "" : "is-invalid"
          }`}
          placeholder="Paste long url to shorten"
          aria-label="Long Url"
          aria-describedby="shorten-url-feedback"
          onChange={(e) => setUrlText(e.target.value)}
        />
        <button
          className="btn btn-primary"
          type="button"
          id="shorten-url-btn"
          onClick={getShortenUrl}>
          Shorten
        </button>
        <div id="shorten-url-feedback" className="invalid-feedback">
          {validateUrl.msg}
        </div>
      </div>
    </form>
  );

  return (
    <>
      {navbar}
      <div className="container">
        <div className="row mt-4 align-items-center">
          <div className="col">{shortenForm}</div>
        </div>
        <div className="row mt-4 align-items-center">
          <table className="table table-responsive border-primary">
            <thead>
              <tr>
                <th>Long Url</th>
                <th>Short Code</th>
                <th>Visit Count</th>
              </tr>
            </thead>
            <tbody>
              {urls.map((url) => {
                return (
                  <tr key={url.id}>
                    <td title={url.longUrl}>{truncateString(url.longUrl)}</td>
                    <td>
                      <a href={url.shortCode}>{url.shortCode}</a>
                    </td>
                    <td>{url.visitCount}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}

export default Home;
