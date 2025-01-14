package krasa.grepconsole.gui.table.column;

import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.IconLoader;
import krasa.grepconsole.gui.MainSettingsForm;
import krasa.grepconsole.gui.SoundSettingsForm;
import krasa.grepconsole.model.GrepExpressionItem;
import krasa.grepconsole.model.Sound;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Vojtech Krasa
 */
public class SoundColumn extends IconColumnInfo {
	public static final Icon SOUND_OFF = IconLoader.getIcon("/krasa/grepconsole/icons/soundOff.png");
	public static final Icon SOUND_ON = IconLoader.getIcon("/krasa/grepconsole/icons/soundOn.png");
	private final MainSettingsForm mainSettingsForm;
	protected SoundSettingsForm soundSettingsForm;

	public SoundColumn(String sound, MainSettingsForm mainSettingsForm) {
		super(sound);
		this.mainSettingsForm = mainSettingsForm;
		soundSettingsForm = new SoundSettingsForm();
	}

	private boolean showDialog(GrepExpressionItem item) {
		DialogBuilder builder = new DialogBuilder(mainSettingsForm.getRootComponent());
		builder.setCenterPanel(soundSettingsForm.getRoot());
		builder.setDimensionServiceKey("GrepConsoleSound");
		builder.setTitle("Sound settings");
		builder.removeAllActions();
		builder.addOkAction();
		builder.addCancelAction();

		soundSettingsForm.setData(item.getSound());
		boolean isOk = builder.show() == DialogWrapper.OK_EXIT_CODE;
		if (isOk) {
			soundSettingsForm.getData(item.getSound());
		}
		return isOk;
	}

	@Override
	protected Icon getIcon(@NotNull GrepExpressionItem value) {
		Sound sound = value.getSound();
		if (sound.isEnabled()) {
			return SOUND_ON;
		} else {
			return SOUND_OFF;
		}
	}

	@Override
	protected void execute(GrepExpressionItem value) {
		showDialog(value);
	}
}
