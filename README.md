# ai-proxy

## 架构设计：三层架构
1. Page层：只关注页面内的元素
2. Window层：操作浏览器各个标签页，并调用Page层进行交互，每个Window对象持有一个WebDrive对象，避免出现交叉管理导致逻辑混乱
3. Service层：只关注Window层的抽象调用

这样做的好处是将浏览器的实际调用权限制在Window层，由Window层统筹管理一个浏览器的多个标签页，避免出现逻辑混乱

