import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { urlService } from "../services/urlService";

export default function ShortUrlRedirect() {
  const [longUrl, setLongUrl] = useState(null);
  let params = useParams();
  useEffect(() => {
    return urlService.getByCode(params.code).then((url) => {
      const lUrl = url.longUrl;
      setLongUrl(lUrl);
      setTimeout(() => {
        window.location.href = lUrl;
      }, 2000);
    });
  }, []);

  return (
    <div className="alert alert-primary text-center" role="alert">
      You will be redirected after 2s to {longUrl}
    </div>
  );
}
