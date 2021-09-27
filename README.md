# Endworldreset-Plugin
Reset World The End automaticaly in periodically.

## Feature
- Schedule the world end reset
- Support PlaceholderAPI
- Easy to use

## Usage
```
[Usages]:
/endworldreset reload           Reload config plugin
/endworldreset autogen          Auto generate config
/endworldreset info             Show time left
/endworldreset info setting     Show config info
```

## Default Config
config.yml :
```
Config :
  # d=day w=week m=month
  every : 1w
  time : 00:00

Save :
  #keep null if u want auto-generate
  #if u want specify by yourself use format dd-MM-yyyy
  nextReset : null
```

## Permissions
| Permission          | Description                  |
|---------------------|-------------------------------|
| endworldreset.admin | Give permission to control plugin setting |


## PlaceholderAPI
| key                 | Description                   |
|---------------------|-------------------------------|
| %ewr_info%          | Show time left to reset world |
| %ewr_nextreset%     | Show reset world Date Time    |
