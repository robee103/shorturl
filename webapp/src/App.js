import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import ShortUrlRedirect from "./components/ShortUrlRedirect";

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact={true} component={Home} />
        <Route path="/:code" component={ShortUrlRedirect} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
