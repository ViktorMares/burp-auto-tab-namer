# Burp Auto Tab Namer

Automatically names Repeater tabs based on HTTP method, path, and optional query/host.

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
