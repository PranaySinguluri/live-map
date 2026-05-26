# LiveMap — Live Location Sharing

Mobile web app for sharing live location between two users. Pin to iPhone home screen for widget-like access.

## What's inside

- **backend/** — Spring Boot REST API (Java 17, Maven)
- **frontend/** — Single HTML file (Leaflet + OpenStreetMap, no API key needed)

## Run the backend

```bash
cd backend
./mvnw spring-boot:run
# or if no mvnw: mvn spring-boot:run
```

Backend runs on `http://localhost:8080`.

API endpoints:
- `POST /api/locations/{userId}` — body: `{"latitude": 34.05, "longitude": -118.24}`
- `GET /api/locations` — returns all current locations
- `GET /api/locations/{userId}` — single user

## Run the frontend

Easiest: open `frontend/index.html` directly in a browser. For mobile testing, serve it:

```bash
cd frontend
python3 -m http.server 3000
# open http://YOUR_LOCAL_IP:3000 on your phone
```

Make sure both phones are on the same network as the backend, OR deploy the backend to a public host (Render, Railway, Fly.io — all have free tiers).

## Deploying to production (so you can use it anywhere)

1. **Backend**: Deploy to Render or Railway — push to GitHub, connect, done. Free tier works.
2. **Frontend**: Update `API_BASE` in `index.html` to your deployed backend URL, then host the HTML on Netlify, Vercel, or GitHub Pages (also free).
3. On iPhone: open the site in Safari → Share → Add to Home Screen. Tap the icon = opens fullscreen, refreshes location, shows both pins.

## How it works

- One user selects "I am Pranay", the other selects "I am Girlfriend"
- Tap the icon on home screen → opens the app → grabs your GPS → sends to backend → fetches both locations → shows both pins on the map
- Auto-refreshes every 15 seconds while open
- "Refresh" button forces an immediate update

## Customizing

- Change usernames in `index.html` (search for "pranay" and "girlfriend")
- Change map default location (line: `setView([34.0522, -118.2437], 13)`) — currently LA, change to Halifax: `[44.6488, -63.5752]`
- Persistence: backend currently uses in-memory storage (locations lost on restart). For persistence, add Spring Data JPA + an embedded H2 database, or Redis.

## Notes

- iOS requires HTTPS for geolocation in production. localhost is fine for testing.
- The home screen icon is a basic emoji placeholder — replace with a real PNG for polish.
