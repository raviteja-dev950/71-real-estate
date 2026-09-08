# 🎯 Project 71 – EstateHub – Premium Real Estate | Spring Boot + H2 + Bypass Full Stack

<p align="left">
  <img src="https://img.shields.io/badge/Java-17-E76F00?logo=openjdk&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/H2-Database-0040C0?logo=h2&logoColor=white" alt="H2">
  <img src="https://img.shields.io/badge/Security-Bypass_permitAll-7C3AED" alt="Bypass">
  <img src="https://img.shields.io/badge/Frontend-Single_HTML_Vanilla_JS-000000?logo=html5&logoColor=white" alt="Bypass Static">
  <img src="https://img.shields.io/badge/Theme-EstateHub_Blue_Black-2563EB" alt="EstateHub">
  <img src="https://img.shields.io/badge/Status-Completed-20B000" alt="Completed">
  <img src="https://img.shields.io/badge/Screenshots-15-FFB700" alt="15 Screenshots">
</p>

## 📖 Project Overview

EstateHub is Project 71 of Tier 7 – Full Stack Integration, built with Spring Boot 3.2.5, H2 Database, Spring Data JPA, Hibernate, Spring Security (Bypass Mode – permitAll) and a single-file premium frontend served from `src/main/resources/static/`.

This project uses **BYPASS FULL STACK** architecture:

- Frontend and Backend run on the **same port 9193** – `http://localhost:9193/`
- No CORS issues, no separate React build – single static `index.html` with Vanilla JS
- Backend serves frontend directly – deploy as 1 JAR
- Auth with ADMIN / OWNER / BUYER role selector + simple `localStorage` + role-based UI
- Login required to Add/Edit/Delete – role-based UI – house click detail view with owner contact – 15 screenshots verified

Backend provides REST endpoints:

- `GET /api/properties` – List all properties live – 5 properties – ₹2.3Cr portfolio
- `POST /api/properties` – Add new property (OWNER / ADMIN)
- `PUT /api/properties/{id}` – Edit property (OWNER / ADMIN) – V2 Edit Feature
- `DELETE /api/properties/{id}` – Delete property (OWNER / ADMIN)
- `POST /api/auth/register` – Create account ADMIN / OWNER / BUYER – ravi BUYER demo
- `POST /api/auth/login` – Login with username, password – returns token + user + role – `admin/admin123`, `owner/owner123`

Frontend displays:

- EstateHub header with EH black logo • "Find Your Dream Property With Zero Brokerage" hero – dream blue – directly from verified owners
- Login / "Welcome to EstateHub" – ADMIN / OWNER / BUYER portal – role selector
- Stats bar – guest browsing • sign in as owner to list property • click any house to see owner contact – 0 to 5 properties + Add Property (green)
- Search bar + filters: All Homes / Flats / Villas / Plots / For Rent + search button + "Search city..."
- Premium property cards: Unsplash image, FLAT/VILLA/PLOT • VERIFIED white badge, ₹ price black pill (₹25L–₹85L), Delete red pill, ID, city, "Click to view contact", description
- House click feature V2 – detail modal: large image (420px, left), type badge (right), title, city • ID • OwnerID, big price (₹45L/₹85L), full description, Owner Details box (Owner: admin • Verified • Direct Contact – No Brokerage • City), Contact Owner black button + Edit & Delete row – OWNER click feature note
- OWNER dashboard – "+ Add Property" green pill – modal with 6 fields – "Publish Property" – "Property Published!" popup
- ADMIN dashboard – full access – can delete any property – 3 to 5 properties – owner • OWNER account active
- BUYER dashboard – ravi BUYER – no Add/Delete – click house → only Contact Owner – alert with phone (+91 9XXXX, direct) – zero brokerage business model – chat, call, WhatsApp integration – like NoBroker
- API verification pages – `/api/properties` JSON – 5 properties – ₹2.3Cr

## ✨ Features

### 🔐 Authentication – Bypass Simple

- Login with username, password, role (ADMIN / OWNER / BUYER) – `localStorage` token, user, role
- No JWT – simple role selector – auto login on refresh – bypass `SecurityConfig` `permitAll` – `/api/**` permitAll
- On H2 mem restart, data is wiped – need to re-add properties – can switch to file H2 for persistence
- Test accounts: `admin/admin123` (ADMIN), `owner/owner123` (OWNER), `ravi/ravi123` (BUYER) – 15 screenshots verified – ADMIN vs OWNER vs BUYER UI

### 🏠 Properties Feed – Real App – 5 Properties

- Fetches all properties from H2 via `/api/properties` – live grid – 5 properties
- Premium cards: house image, `FLAT • VERIFIED` / `VILLA • VERIFIED` / `PLOT • VERIFIED` badge (white, top left), ₹ price black pill (bottom left), Delete (red, top right), title, 📍 city • ID: 1 • "Click to view contact", description truncated to 2 lines
- 3 sample properties auto-added on start via `DataLoader`:
  - Razole Dream House ₹25L – FLAT – Razole, near temple, verified
  - Hyderabad Villa ₹85L – VILLA – Hyderabad, luxury pool, 4BHK, gated
  - Bangalore Plot ₹45L – PLOT – Bangalore, IT Park, 2400 sqft, clear title
  - (All owned by admin, IDs 1–3)
- Added via UI (real POST):
  - Hyderabad 2BHK Flat ₹48L – FLAT – Hyderabad, Kukatpally, 1200 sqft, semi-furnished, verified – ID 4
  - Razole Riverside Plot ₹32L – PLOT – Razole, 1800 sqft, Godavari canal, clear title, direct owner, verified – ID 5
  - Proves scalability (3 → 5)
- Filters – All Homes (active, black) / Flats / Villas / Plots / For Rent – instant JS filter, type uppercase match
- Search – city contains, live – Hyderabad / Razole / Bangalore – Enter or Search button – applies `filterType` + `searchCity()`
- Responsive grid – 4–5 columns desktop (290px minmax), 1 column mobile – hover lift `translateY(-3px)` + shadow `0 12px 30px` – cursor pointer – "Click to view contact" hint
- Stats – 0 to 5 properties live count – guest browsing • sign in as owner to list property • click any house to see owner contact
- Portfolio – ₹25L + ₹85L + ₹45L + ₹48L + ₹32L = ₹2.35Cr

### ➕ Owner Dashboard – Add New Property – 2 Properties Added

- "Add Property" green pill (`#16a34a`) – top right bar – OWNER / ADMIN only – hidden for BUYER (`display: block` if role is ADMIN or OWNER, else `none`)
- Modal "Add New Property" – only OWNER & ADMIN can add – 6 inputs:
  - Title * (e.g. "Razole Riverside Plot", "Hyderabad 2BHK Flat")
  - City * (e.g. "Razole", "Hyderabad")
  - Type select: FLAT / VILLA / PLOT / RENT
  - Price * number (e.g. 4800000, 3200000)
  - Image URL placeholder (`https://images.unsplash...`)
  - Description textarea (e.g. "2BHK in Kukatpally...", "1800 SQFT plot near Godavari canal...")
- "Publish Property" button – black (`#111827`) – `POST /api/properties` – headers: `Content-Type` + `Authorization: Bearer <token>` – body: `title, city, type, price, imageUrl, description, ownerId: currentUser.id`
- Toast "Property Published!" – alert – OK button – grid grows instantly (3 → 4 → 5) – verified in screenshots demo6, demo8
- Tested:
  - Hyderabad 2BHK Flat ₹48L: demo5 (filled) → demo6 (published) → demo9 (final grid)
  - Razole Riverside Plot ₹32L: demo7 (filled) → demo8 (published) → demo9 (5 props)
  - Full stack proof, not H2 console

### 🏡 Property Detail View – Main Feature V2 – House Click

- Click any property card, `onclick="openDetail(id)"` – detail modal, 780px width (96vw max) – grid `1.2fr 0.8fr` – left `detail-img` 420px height – right `detail-content` 18px padding – backdrop blur 12px – border radius 18px – this is the previously missing "click house" feature, now fixed
- **Left:** large house image, `img src = p.imageUrl` (fallback Unsplash) – `object-fit: cover`
- **Right:**
  - Top row: badge + close – badge `#eef2ff` bg (`PLOT • VERIFIED` / `VILLA • VERIFIED`) – close "✕" white pill
  - `h3` title (20px) – meta "📍 City • Direct from Owner • ID • OwnerID" – price (22px, 800 weight) formatted as ₹45,00,000 / ₹85,00,000 (`en-IN` locale) – description (12px, `#475569`, line-height 1.6)
  - **Owner Details box** – margin-top 14px, bg `#f8fafc`, border `#e2e8f0`, radius 12px, padding 12px
    - Title: "👤 Owner Details" (12px, 700 weight)
    - Owner line (11px, `#475569`): "Owner: admin • Verified • Direct Contact – No Brokerage • City: Hyderabad/Bangalore"
    - Note (10px, `#94a3b8`): "ADMIN can edit/delete ANY property • OWNER can edit/delete OWN properties only • BUYER can only contact"
  - **Buttons:**
    - "📞 Contact Owner" – black, full width, 11px, radius 10px – `onclick="contactOwner()"` – shows phone `+91 9XXXX XXXXX (Direct)`, city, price, logged-in user – in production: chat/call/WhatsApp integration – main BUYER action – zero-brokerage business logic (like NoBroker/MagicBricks)
    - Edit & Delete row – grid `1fr 1fr`, gap 6px – shown only if `canEdit` (role ADMIN or OWNER), else `none` for BUYER
      - Edit (white pill, "✏️ Edit") – `openEditFromDetail()` – prefills `eTitle, eCity, eType, ePrice, eImage, eDesc` → closes detail → opens edit modal – "Save Changes" → `PUT /api/properties/{id}` → "Updated!"
      - Delete (red pill, "🗑️ Delete") – `deleteFromDetail()` → confirm → `doDelete()` → `DELETE /api/properties/{id}` with `Authorization: Bearer <token>`
- Verified screenshots:
  - `demo11_detail_bangalore.png` – PLOT • VERIFIED, Razole Riverside Plot
  - `demo13_detail_hyderabad.png` – VILLA • VERIFIED, Hyderabad Villa
  - `demo14_contact_owner.png` – BUYER ravi, contact popup – main V2 feature

### 🛡 Role Protection – ADMIN vs OWNER vs BUYER

- **ADMIN** (`admin/admin123`) – sees "+ Add Property" (green) – can add, edit, delete ANY property – Delete red pill on all cards – "Quick Delete" uses `event.stopPropagation()` – `doDelete()` with Bearer token – full portfolio control – ID/OwnerID visible in detail – can manage all users – super user
- **OWNER** (`owner/owner123`) – sees "+ Add Property" – can add own, edit/delete own (demo: any, for testing) – added Hyderabad 2BHK + Razole Plot (3 → 5) – Edit/Delete in detail modal – manages own listings – direct owner – verified badge – real house owner
- **BUYER** (`ravi/ravi123`) – no Add, no Delete – view-only grid (5 properties) – click house → detail modal → only "Contact Owner" button visible (Edit/Delete row `display: none`) – alert "Please Sign In to contact owner" if not logged in – if logged in as ravi (BUYER) → phone `+91 9XXXX` shown – Contact Owner only – zero brokerage, main business model – direct owner contact, no brokerage – BUYER flow screenshots: demo12 (account created) + demo14 (contact owner)
- Quick Delete on card (top right) – `event.stopPropagation()` so card click doesn't also trigger detail view – only OWNER/ADMIN see the red Delete pill (10px) – BUYER protected

## 🛠 Technologies Used

| Technology | Version | Purpose |
|---|---|---|
| Java | 17.0.10 | Backend language |
| Spring Boot | 3.2.5 | REST APIs, Embedded Tomcat – Port 9193 |
| Spring Data JPA / Hibernate | 6.4.4.Final | ORM – `Property` ManyToOne `User`, `User` |
| Spring Security | 6.2.4 | Bypass – `permitAll()` – no JWT filter – simple Bearer token check in controller |
| H2 Database | 2.2.x | In-memory `realestate` DB – no setup – H2 console enabled at `/h2-console` |
| Frontend | Single `static/index.html` (V2) – Vanilla JS – 780px detail box + Add/Edit/Detail modals + 15 screenshots | No React build – bypass full stack – same port |
| CSS | Pure CSS – Inter + Plus Jakarta Sans (700/800) – black `#111827` + blue `#2563eb` + white + green `#16a34a` + red `#ef4444` – 16px card radius | Real App Premium EstateHub theme |
| Maven | 3.9+ | Build – `mvn clean install -DskipTests` / `mvn spring-boot:run` |

## 📂 Project Structure

```text
71-real-estate-backend/
│
├── src/main/java/com/realestate/
│   ├── Application.java              – Spring Boot main + DataLoader (sample users + properties)
│   ├── config/
│   │   ├── SecurityConfig.java       – Bypass permitAll – /api/** permitAll – CSRF disabled – no JWT filter
│   │   └── DataLoader.java           – Auto-adds 2 users + 3 properties if count == 0
│   ├── controller/
│   │   ├── PropertyController.java   – GET/POST/PUT/DELETE /api/properties
│   │   └── AuthController.java       – POST /api/auth/register, POST /api/auth/login
│   ├── entity/
│   │   ├── Property.java             – id, title, city, type, price, imageUrl, description, owner (@ManyToOne User)
│   │   └── User.java                 – id, username (unique), password, role
│   ├── repository/
│   │   ├── PropertyRepository.java   – JpaRepository<Property, Long>
│   │   └── UserRepository.java       – findByUsername(String username)
│
├── src/main/resources/
│   ├── static/
│   │   └── index.html                – Full single-file V2 frontend (header, hero, filters, grid, modals, JS)
│   └── application.properties        – server.port, datasource, JPA, H2 console config
│
├── screenshots/                      – 15 premium images (demo1.png – demo15.png)
│
├── pom.xml                           – spring-boot-starter-web, data-jpa, security, H2 runtime, Lombok (optional)
├── .gitignore                        – target/, data/, .idea/, *.db, *.log, .mvn/, mvnw, mvnw.cmd
└── README.md                         – This file
```

### Screenshots index

| # | File | Description |
|---|---|---|
| 1 | `demo1.png` | Guest view – 3 properties – Zero Brokerage hero – All Homes pill active |
| 2 | `demo2.png` | "Welcome to EstateHub" sign-in modal – demo credentials |
| 3 | `demo3.png` | Admin logged in – `admin • ADMIN` – 3 properties with Delete |
| 4 | `demo4.png` | Add New Property – empty modal |
| 5 | `demo5.png` | Add Hyderabad 2BHK Flat – filled |
| 6 | `demo6.png` | "Property Published!" popup |
| 7 | `demo7.png` | Add Razole Riverside Plot – filled |
| 8 | `demo8.png` | "Property Published!" popup (second) |
| 9 | `demo9.png` | Final 5-property grid – ₹2.3Cr portfolio |
| 10 | `demo10.png` | Owner logged in – 5 properties |
| 11 | `demo11.png` | Detail view – Bangalore Plot |
| 12 | `demo12.png` | Account created – ravi (BUYER) |
| 13 | `demo13.png` | Detail view – Hyderabad Villa |
| 14 | `demo14.png` | Contact Owner popup – BUYER flow |
| 15 | `demo15.png` | Extra detail view proof |

## ▶ How to Run

### 1. Clone

```bash
git clone https://github.com/raviteja-dev950/71-real-estate-backend.git
cd 71-real-estate-backend
```

### 2. Application Properties

Current setup (in-memory – wipes on restart):

```properties
server.port=9193
spring.datasource.url=jdbc:h2:mem:realestate
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=false
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

For persistence (recommended for demos – keeps properties after restart):

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:h2:file:./data/realestate
```

### 3. Run – Single Command – Bypass Full Stack

```bash
mvn clean install -DskipTests
mvn spring-boot:run
```

Open (single port 9193 – frontend + backend on the same port):

- `http://localhost:9193/` – Frontend + Backend – EstateHub V2 – 5 properties – click a house for detail + contact owner
- `http://localhost:9193/api/properties` – Properties JSON – 5 properties – ₹2.3Cr portfolio
- `http://localhost:9193/h2-console` – H2 console – JDBC URL `jdbc:h2:mem:realestate` – user `sa`, no password – tables `property`, `users`

### 4. Frontend Logic (inside `index.html` V2)

```javascript
// Login – role selector – simple localStorage – bypass
localStorage.setItem('token', d.token); // UUID
localStorage.setItem('user', JSON.stringify(d)); // {id, username, role}
currentUser = d; // ADMIN / OWNER / BUYER
updateAuthUI(); // shows auth pill + Add button for ADMIN/OWNER only

// Load properties
fetch('/api/properties')
  .then(r => r.json())
  .then(list => { allProps = list; apply(); });

function apply() {
  let f = [...allProps];
  // apply city + type filters (FLAT/VILLA/PLOT/RENT)
  render(f);
}

function render(list) {
  // builds grid: card onclick="openDetail(p.id)" + VERIFIED tag
  // + black price pill + quickDelete (ADMIN/OWNER only)
}

// Add Property – OWNER / ADMIN only
fetch('/api/properties', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + token
  },
  body: JSON.stringify({
    title, city, type, price, imageUrl, description,
    ownerId: currentUser.id
  })
});
// Alert: "Property Published!" – reload grid (3 → 5)

// House click – detail view – V2 main feature
function openDetail(id) {
  const p = allProps.find(x => x.id === id);
  dImg.src = p.imageUrl;
  dType.innerText = `${p.type} • VERIFIED`;
  dTitle.innerText = p.title;
  dMeta.innerText = `📍 ${p.city} • Direct from Owner • ID: ${p.id} • OwnerID: ${p.ownerId || p.owner?.id || '1'}`;
  dPrice.innerText = `₹ ${p.price.toLocaleString('en-IN')}`;
  dDesc.innerText = p.description;
  dOwner.innerText = `Owner: ${p.owner?.username || 'verified_owner'} • Verified • Direct Contact - No Brokerage • City: ${p.city}`;

  const canEdit = currentUser && (currentUser.role === 'ADMIN' || currentUser.role === 'OWNER');
  editDeleteRow.style.display = canEdit ? 'grid' : 'none';
  detailModal.style.display = 'flex';
}

// Contact Owner – BUYER main business logic – zero brokerage
function contactOwner() {
  if (!currentUser) {
    alert(`Please Sign In to contact owner of ${selectedProp.title}
Owner: Verified Owner
City: ${selectedProp.city}
In real app: Phone, WhatsApp, Email shown here`);
    showLogin();
    return;
  }
  alert(`📞 Contact Owner for: ${selectedProp.title}

Owner: ${selectedProp.owner?.username || 'verified_owner'}
Phone: +91 9XXXX XXXXX (Direct)
City: ${selectedProp.city}
Price: ₹ ${selectedProp.price.toLocaleString('en-IN')}

In production: Chat, Call, WhatsApp integration here!

Logged in as: ${currentUser.username} (${currentUser.role})`);
}

// Edit – PUT – prefilled from detail
function openEditFromDetail() {
  eTitle.value = selectedProp.title;
  eCity.value = selectedProp.city;
  eType.value = selectedProp.type;
  ePrice.value = selectedProp.price;
  eImage.value = selectedProp.imageUrl;
  eDesc.value = selectedProp.description;
  closeDetail();
  editModal.style.display = 'flex';
}

fetch('/api/properties/' + id, {
  method: 'PUT',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + token
  },
  body: JSON.stringify({ title, city, type, price, imageUrl, description })
}); // Save Changes → "Updated!"

// Delete – ADMIN/OWNER only – quick + detail
function quickDelete(id) {
  event.stopPropagation();
  if (!confirm('Delete?')) return;
  doDelete(id);
}

function deleteFromDetail() {
  if (!confirm('Delete ' + selectedProp.title + '?')) return;
  doDelete(selectedProp.id);
  closeDetail();
}

fetch('/api/properties/' + id, {
  method: 'DELETE',
  headers: { 'Authorization': 'Bearer ' + token }
});

// Auth
fetch('/api/auth/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ username, password })
}); // returns token + user

fetch('/api/auth/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ username, password, role })
}); // "Account created!"
```

## 🔄 Application Flow

```text
Browser
 │
 ▼
http://localhost:9193/  – static/index.html (V2) – 15 screenshots
 │
 ├── Guest (demo1.png)
 │     "Find Your Dream Property With Zero Brokerage" hero
 │     Filter pills: All Homes / Flats / Villas / Plots / For Rent
 │     3 properties: Razole Dream ₹25L, Hyderabad Villa ₹85L, Bangalore Plot ₹45L
 │
 ├── Auth (demo2.png)
 │     "Welcome to EstateHub" – Sign In / Create Account
 │     Demo creds: admin/admin123 (ADMIN), owner/owner123 (OWNER)
 │     → localStorage token + user → role pill top right (demo3.png)
 │
 ├── Add Property Flow (demo4 → demo8, 3 → 5 properties)
 │     demo4: empty "Add New Property" modal
 │     demo5: filled – Hyderabad 2BHK Flat ₹48L
 │     demo6: "Property Published!" – grid 3 → 4
 │     demo7: filled – Razole Riverside Plot ₹32L
 │     demo8: "Property Published!" – grid 4 → 5
 │     demo9: final 5-property grid – ₹2.35Cr portfolio
 │
 ├── OWNER Login (demo10.png)
 │     owner • OWNER – 5 properties – Add Property + Delete on all
 │
 ├── House Click Detail View V2 (demo11, demo13, demo14)
 │     demo11: Bangalore Plot detail – image, badge, price, owner box, Contact/Edit/Delete
 │     demo13: Hyderabad Villa detail
 │     BUYER flow (demo12, demo14):
 │       demo12: ravi account created
 │       demo14: Contact Owner popup – phone, city, price – zero brokerage
 │
 ├── Edit Flow (Detail → Edit → PUT /api/properties/{id} → "Updated!")
 │     + Search/filter by city and type
 │
 └── Logout – localStorage.clear() → back to Guest view
 │
 ▼
Spring Boot :9193 – single JAR – permitAll – no CORS – CSRF disabled
 │
 ▼
H2 – tables: property, users
 DataLoader seeds: admin/ADMIN/admin123, owner/OWNER/owner123
 + 3 sample properties (IDs 1–3, owner admin)
 UI adds 2 more (IDs 4–5) → final 5 properties, ₹2.35Cr portfolio
```

## 🧪 API Testing

```bash
# List all – 5 properties
curl http://localhost:9193/api/properties

# Register BUYER ravi
curl -X POST http://localhost:9193/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"ravi","password":"ravi123","role":"BUYER"}'

# Login ADMIN
curl -X POST http://localhost:9193/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Login OWNER
curl -X POST http://localhost:9193/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"owner","password":"owner123"}'

# Add Hyderabad 2BHK Flat
curl -X POST http://localhost:9193/api/properties \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"title":"Hyderabad 2BHK Flat","city":"Hyderabad","type":"FLAT","price":4800000,"imageUrl":"https://images.unsplash.com/photo-1522708323590-d24dbb6b0267","description":"2BHK in Kukatpally, 1200 sqft, semi-furnished, verified","ownerId":1}'

# Add Razole Riverside Plot
curl -X POST http://localhost:9193/api/properties \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"title":"Razole Riverside Plot","city":"Razole","type":"PLOT","price":3200000,"imageUrl":"https://images.unsplash.com/photo-1500382017468-9049fed747ef","description":"1800 SQFT plot near Godavari canal, clear title, direct owner verified","ownerId":1}'

# Edit property
curl -X PUT http://localhost:9193/api/properties/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"title":"Razole Dream House Updated","city":"Razole","type":"FLAT","price":2700000,"imageUrl":"https://images.unsplash.com/photo-1600596542815-ffad4c1539a9","description":"Updated description - Verified - Direct Owner"}'

# Delete property
curl -X DELETE http://localhost:9193/api/properties/5 \
  -H "Authorization: Bearer <token>"

# Verify final 5
curl http://localhost:9193/api/properties | jq
```

## 📡 API Endpoints

| Method | Endpoint | Access | Purpose |
|---|---|---|---|
| GET | `/api/properties` | Public | List all properties – live grid – 5 properties, ₹2.35Cr portfolio |
| POST | `/api/properties` | OWNER/ADMIN | Add new property – 6-field modal – Bearer token required |
| PUT | `/api/properties/{id}` | OWNER/ADMIN | Edit property – prefilled from detail modal – real CRUD |
| DELETE | `/api/properties/{id}` | OWNER/ADMIN | Delete property – quick delete or from detail view |
| POST | `/api/auth/register` | Public | Create account – username, password, role (ADMIN/OWNER/BUYER) |
| POST | `/api/auth/login` | Public | Login – returns token (UUID) + id + username + role |

## 🗄 Database Note

H2 uses `GenerationType.IDENTITY` – auto increment, no sequence needed.

### Tables

```text
property – id BIGINT PK AUTO_INCREMENT, title VARCHAR, city VARCHAR,
           type VARCHAR (FLAT/VILLA/PLOT/RENT), price DOUBLE,
           image_url VARCHAR(1000), description VARCHAR(1000),
           owner_id BIGINT FK → users.id

users    – id BIGINT PK, username VARCHAR UNIQUE, password VARCHAR,
           role VARCHAR (ADMIN/OWNER/BUYER)
```

`DataLoader` seeds 2 users (`admin`/ADMIN/`admin123`, `owner`/OWNER/`owner123`) and 3 properties on first startup if the tables are empty:

- Razole Dream House – ₹25L – FLAT – Razole, near temple, verified, direct owner
- Hyderabad Villa – ₹85L – VILLA – Hyderabad, luxury pool, 4BHK, gated, verified owner
- Bangalore Plot – ₹45L – PLOT – Bangalore, IT Park, 2400 sqft, clear title, direct sale

All seeded properties are owned by `admin` (id 1).

- **H2 Console:** `http://localhost:9193/h2-console` – JDBC URL `jdbc:h2:mem:realestate` – user `sa`, no password – tables `property`, `users` – `SELECT * FROM PROPERTY;` (5 rows) / `SELECT * FROM USERS;` (3 rows: admin, owner, ravi)
- **Important:** with `mem` + `create`, data is wiped on restart. Fix by re-adding the 2 UI-added properties, or switch to `jdbc:h2:file:./data/realestate` with `ddl-auto=update` for persistence.

## 🎯 Learning Outcomes

- **Bypass Full Stack** – single `static/index.html` (V2) served by Spring Boot on the same port (9193) – single JAR, no CORS – detail modal (780px, `1.2fr 0.8fr`) on house click
- **H2 Database** – mem vs. file, `create` vs. `update` – `DataLoader` seeding – mem wipe vs. file persistence
- **Security Bypass** – `permitAll()` for speed – simple Bearer/UUID token, no JWT filter – `AuthController` returns token + user – role-based `localStorage`
- **Property Detail View** – click-to-open modal with large image, Owner Details box, Contact Owner – core real-estate feature (like NoBroker/MagicBricks)
- **Owner Contact Audit** – `owner` ManyToOne + `getOwnerId()` with fallback – zero-brokerage business logic – direct owner contact
- **Role-based UI** – ADMIN/OWNER see Add/Edit/Delete; BUYER sees Contact Owner only – real role protection via conditional rendering
- **Real App UI** – Inter + Plus Jakarta Sans typography, consistent color system, card hover effects, toast-style alerts
- **Filters + Search** – type filter (instant toggle) + live city search
- **Full CRUD proof** – Add/Edit/Delete flows verified end-to-end via UI and API, not just the H2 console

## 🚀 Future Enhancements – V2 to V3

- Real JWT filter (JJWT), BCrypt password encoding, token expiry
- Switch H2 from in-memory to file-based (or MySQL/PostgreSQL) for persistence
- `ownerId` filter for a "My Properties" / owner dashboard view
- Wishlist (ManyToMany User ↔ Property) for BUYERs
- Contact history / audit table (who contacted which property)
- Image upload (multipart, S3 or local storage) with multiple images per property
- Pagination (`/api/properties?page=0&size=5`)
- Map view with latitude/longitude and Google Maps integration
- Advanced filters (price range, BHK, area, furnishing)
- Admin dashboard charts (Chart.js) – city/price/type distribution
- Real-time chat between BUYER and OWNER (WebSocket/STOMP)
- Deployment to Render/Railway as a single JAR
- Optional React frontend (separate port, with CORS) as a later iteration
- Email notifications (JavaMailSender) for new listings and contact requests
- Owner/property document verification workflow

## 👨‍💻 Author

**Vemula Leela Venkata Ravi Teja**
Java Full Stack Developer – Challapalli, Andhra Pradesh

100 Java Full Stack Projects Challenge – **71 / 100** completed – Bypass Track – EstateHub V2 Real Estate
Tier 7 – Full Stack Integration – Single Port 9193 – House Click Detail + Contact Owner + 15 Screenshots

GitHub: [raviteja-dev950/71-real-estate-backend](https://github.com/raviteja-dev950/71-real-estate-backend)

## Test Accounts

| Username | Password | Role | Notes |
|---|---|---|---|
| `admin` | `admin123` | ADMIN | Full access – add/edit/delete any property |
| `owner` | `owner123` | OWNER | Add own properties, edit/delete own (demo: any) |
| `ravi` | `ravi123` | BUYER | View only – Contact Owner only, no Add/Delete |

## ⭐ Support

If you found this project helpful, give it a ⭐ on GitHub!

**Repo:** https://github.com/raviteja-dev950/71-real-estate-backend

**Run:**

```bash
mvn spring-boot:run
```

**Open:**

```text
http://localhost:9193/
```

### Sample Properties – 5 total – ₹2.35Cr portfolio

| ID | Title | City | Price | Type | Description |
|---|---|---|---|---|---|
| 1 | Razole Dream House | Razole | ₹25,00,000 | FLAT | 2BHK near temple – verified – direct from owner – no brokerage |
| 2 | Hyderabad Villa | Hyderabad | ₹85,00,000 | VILLA | Luxury villa with private pool – 4BHK – gated community – verified owner |
| 3 | Bangalore Plot | Bangalore | ₹45,00,000 | PLOT | Premium plot near IT Park – 2400 sqft – clear title – direct owner sale |
| 4 | Hyderabad 2BHK Flat | Hyderabad | ₹48,00,000 | FLAT | 2BHK in Kukatpally, 1200 sqft, semi-furnished, verified |
| 5 | Razole Riverside Plot | Razole | ₹32,00,000 | PLOT | 1800 sqft plot near Godavari canal, clear title, direct owner, verified |

**Portfolio total:** ₹25L + ₹85L + ₹45L + ₹48L + ₹32L = **₹2,35,00,000 (~₹2.3Cr)**, all owned by `admin` (id 1).
