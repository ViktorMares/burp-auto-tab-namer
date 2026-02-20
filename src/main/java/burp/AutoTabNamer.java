package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;

import burp.api.montoya.http.message.requests.HttpRequest;

import javax.swing.JMenuItem;
import java.awt.Component;
import java.util.List;

public class AutoTabNamer implements BurpExtension, ContextMenuItemsProvider {

    private MontoyaApi api;

    private boolean includeHost = false;
    private boolean includeQuery = false;

    @Override
    public void initialize(MontoyaApi api) {
        this.api = api;

        api.extension().setName("Auto Tab Namer");

        api.userInterface().registerContextMenuItemsProvider(this);
    }

    @Override
    public List<Component> provideMenuItems(ContextMenuEvent event) {

        JMenuItem pathOnly = new JMenuItem("Send to Repeater (Path)");
        JMenuItem pathQuery = new JMenuItem("Send to Repeater (Path + Query)");
        JMenuItem hostPath = new JMenuItem("Send to Repeater (Host + Path)");
        JMenuItem full = new JMenuItem("Send to Repeater (Full)");

        pathOnly.addActionListener(e -> handle(event, false, false));
        pathQuery.addActionListener(e -> handle(event, false, true));
        hostPath.addActionListener(e -> handle(event, true, false));
        full.addActionListener(e -> handle(event, true, true));

        return List.of(pathOnly, pathQuery, hostPath, full);
    }

    private void handle(ContextMenuEvent event, boolean host, boolean query) {

        this.includeHost = host;
        this.includeQuery = query;

        // Case 1: Proxy / HTTP history
        if (!event.selectedRequestResponses().isEmpty()) {

            event.selectedRequestResponses().forEach(rr -> {
                send(rr.request());
            });

        }
        // Case 2: Request editor
        else if (event.messageEditorRequestResponse().isPresent()) {

            HttpRequest request = event.messageEditorRequestResponse()
                    .get()
                    .requestResponse()
                    .request();

            send(request);
        }
    }

    private void send(HttpRequest request) {

        String method = request.method();
        String host = request.httpService().host();
        String path = request.path();

        String cleanPath = path;
        String query = "";

        // Split query manually
        if (path.contains("?")) {
            cleanPath = path.substring(0, path.indexOf("?"));
            query = path.substring(path.indexOf("?"));
        }

        StringBuilder caption = new StringBuilder();

        caption.append(method).append(" ");

        if (includeHost) {
            caption.append(host);
        }

        caption.append(cleanPath);

        if (includeQuery && !query.isEmpty()) {
            caption.append(query);
        }

        api.repeater().sendToRepeater(request, caption.toString());
    }
}