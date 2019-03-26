package lec19.v5;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PlaylistView extends JPanel implements PlaylistObserver {

	private Playlist plist;
	private JPanel list_panel;
	
	public PlaylistView(Playlist plist) {
		this.plist = plist;
		plist.addObserver(this);
		
		setLayout(new BorderLayout());

		list_panel = new JPanel();
		list_panel.setLayout(new GridLayout(0,1));
		buildListPanel();
		add(new JScrollPane(list_panel), BorderLayout.CENTER);
		
		AddSongWidget add_song_widget = new AddSongWidget(plist);
		add(add_song_widget, BorderLayout.SOUTH);
	}

	@Override
	public void playlistChanged(Playlist changed_playlist) {
		list_panel.removeAll();
		buildListPanel();
		list_panel.revalidate();
	}
	
	private void buildListPanel() {
		for (Song s : plist.getSongs()) {
			JLabel song_label = new JLabel(s.toString());
			list_panel.add(song_label);
		}				
	}
}
