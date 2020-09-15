# a-coders-spring

## Endpoints

| Request Type | Endpoint | Header | Parameters | Request Body | Response | Description |
| --- | --- | --- | --- | --- | --- | --- |
| `POST` | `/api/auth/register` | null | null | `username`,`password`,`email` | `token` | Registers user |
| `GET` | `/api/auth/login` | null | `username`,`password` | null | `token` | Logins user |
| `POST` | `/api/trade` | `token` | null | `type`,`ticker`,`quantity`,`price`,`instrument` | null | type:`BUY`,`SELL`; instrument:`stock`,`bond`,`future`,`cash`,`swap`; quantity:`integer`;</br> price:`double`; |
| `GET` | `/api/trade/{username}/portfolio` | `token` | null | null | JSON formatted portfolio of user, grouped by instrument  | details of asset includes: `ticker`,`quantity`,`totalCost`,`instrument` |
| `GET` | `/api/trade/{username}` | `token` | null | null | List of Trades |
| `DELETE` | `/api/account/{username}` | `token` | null | null | null | Deactivates user |
| `PUT` | `/api/account/{username}` | `token` | null | `username`,`password`,`email` | `token` | Updates user |
| `GET` | `/api/market/data` | null | null | null | list of market data | details include: `ticker`; `quotePrice`; `volume`; `open`;`previousClose`;`peRatio` |

