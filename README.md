# Burp Auto Tab Namer

#### Always lost in hundreds of Repeater Tabs during Pentesting or Bug Bounty Hunting? 
#### Same here, this is why I created this simple Burp Suite Extenstion which automatically names Repeater tabs based on HTTP method, path, and optional query/host.
#
## 🎬 Demo
> Quick 30-second overview of how it works

![Demo](assets/auto-tab-namer.gif)

## Features

- Send to Repeater with:
  - Path only
  - Path + Query
  - Host + Path
  - Full (Host + Path + Query)
- Works from:
  - Proxy history
  - Request editor

## Usage

Right-click request → Extensions → Auto Tab Namer
<img width="1239" height="613" alt="image" src="https://github.com/user-attachments/assets/ab4a341b-d834-42a9-ac00-c512d174431a" />


## Installing
1. Go to releases
2. Download the jar file
3. Inside Burp Suite -> Extenstions (Installed Tab) -> Add
<img width="972" height="250" alt="image" src="https://github.com/user-attachments/assets/e49dddff-c189-4193-b216-783dcc5e2492" />
 
## Build

```bash
git clone https://github.com/ViktorMares/burp-auto-tab-namer
```
```bash
cd burp-auto-tab-namer
```
```bash
mvn clean package
```
